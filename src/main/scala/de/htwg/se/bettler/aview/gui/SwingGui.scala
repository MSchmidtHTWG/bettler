package de.htwg.se.bettler
package aview 
package gui

import scala.swing.Swing.LineBorder
import de.htwg.se.bettler.controller.ControllerInterface
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl.Cards
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl.Card
import de.htwg.se.bettler.model.cardComponent._
import scala.swing._
import scala.swing.event._
import scala.swing.Swing.LineBorder
import scala.io.Source._
import javax.swing.border.Border
import java.awt.Color
import javax.swing.JButton
import scala.collection.mutable.ListBuffer
import javax.swing.text.Position
import javax.swing.JTextArea
import javax.swing.JScrollPane
import javax.swing.JOptionPane
import javax.swing.SwingConstants
import javax.imageio.ImageIO
import java.io.File
import scala.language.postfixOps
import javax.swing.ImageIcon
import javax.swing.JPanel
import javax.swing.JCheckBox
import scala.util.Success
import scala.util.Failure
import javax.swing.WindowConstants.EXIT_ON_CLOSE

import scala.swing.Publisher
import scala.swing.event.Event
import de.htwg.se.bettler.controller.CloseEvent
import de.htwg.se.bettler.controller.GameChanged


class SwingGui(controller: ControllerInterface) extends Frame with Reactor{
    var cardsSelected = Set.empty[CardInterface]
    listenTo(controller)
    peer.setDefaultCloseOperation(EXIT_ON_CLOSE)
    reactions += {
        case e : CloseEvent => this.close()
    }

    title = "HTWG-Bettler"
    menuBar = new MenuBar {
    contents += new Menu("Option") {
        mnemonic = Key.F
        contents += new MenuItem(Action("New PvP") {
            controller.doAndNotify(controller.newGame, "pvp")
        })
        contents += new MenuItem(Action("New PvE") {
            controller.doAndNotify(controller.newGame, "pve")
        })
    
    
        contents += new MenuItem(Action("Quit") {controller.exit})
        }
    }
    visible = true
    centerOnScreen()
    redraw
    reactions += {
        case e : GameChanged => redraw
    }


    def buttonPanel: GridPanel = new GridPanel(1,4):
        val playButton = new Button("Play")
        val skipButton = new Button("Skip")
        val undoButton = new Button("Undo")
        val redoButton = new Button("Redo")
        val nextRoundButton = new Button("Next Round")
        val input = new TextArea("") 
        
        contents += input
        contents += playButton
        contents += skipButton 
        contents += undoButton 
        contents += redoButton
        contents += nextRoundButton
        listenTo(input)       
        listenTo(playButton)
        listenTo(skipButton)
        listenTo(undoButton)
        listenTo(redoButton)
        listenTo(nextRoundButton)

        reactions +={
            case ButtonClicked(`playButton`) => 
                controller.doAndNotify(controller.play, Cards(cardsSelected))
                cardsSelected = Set.empty[CardInterface]
            case ButtonClicked(`skipButton`) => controller.doAndNotify(controller.skip)
            case ButtonClicked(`undoButton`) => controller.undo
            case ButtonClicked(`redoButton`) => controller.redo
            case ButtonClicked(`nextRoundButton`) => controller.doAndNotify(controller.nextRound)
        }

    
    def showCards(cards : CardsInterface): BoxPanel = new BoxPanel(Orientation.Horizontal):
        for(card <- cards.returnSet)
            var f = card.image
            var pic = ImageIO.read(f).getScaledInstance(52,80,java.awt.Image.SCALE_SMOOTH)
            val cb = new ToggleButton(card.toString)
            cb.text = ""
            cb.name = card.toString
            cb.selectedIcon = ImageIcon(pic)
            cb.disabledIcon = ImageIcon(ImageIO.read(f).getScaledInstance(46,72,java.awt.Image.SCALE_SMOOTH))
            cb.icon = cb.disabledIcon
            cb.selected = false
            cb.opaque = false
            cb.contentAreaFilled = false
            cb.borderPainted = false
            cb.focusPainted = false
            contents += cb
            listenTo(cb)
            reactions += {
                case ButtonClicked(`cb`) =>
                    if cb.selected then
                        Card(cb.name) match
                            case Success(s) => cardsSelected = cardsSelected + s
                            case Failure(f) => System.exit(0)
                    else 
                        Card(cb.name) match
                            case Success(s) => cardsSelected = cardsSelected - s
                            case Failure(f) => System.exit(0)
            }

    def mainMenuPanel : BoxPanel = new BoxPanel(Orientation.Horizontal):
        val startButton = new Button("Start PvP")
        val startButton2 = new Button("Start PvE")
        contents += startButton  
        contents += startButton2 
        listenTo(startButton)
        listenTo(startButton2)
        reactions += {
            case ButtonClicked(`startButton`) => controller.doAndNotify(controller.newGame, "pvp")
            case ButtonClicked(`startButton2`) => controller.doAndNotify(controller.newGame, "pve")
        }

    def redraw: Unit =
        if !controller.returnGame.isDefined then
            contents = new GridPanel(5,1):
                contents += mainMenuPanel
            return
        contents = new GridPanel(5,1):
            contents += new Label(controller.returnGame.get.getMessage())
            contents += showCards(controller.returnGame.get.getPlayers()(1))  
            contents += showCards(controller.returnGame.get.getBoard())
            contents += showCards(controller.returnGame.get.getPlayers()(0))
            contents += buttonPanel
        repaint()
}
