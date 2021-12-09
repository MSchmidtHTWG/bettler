package de.htwg.se.bettler.aview.gui

import scala.swing.Swing.LineBorder
import de.htwg.se.bettler.controller._
import de.htwg.se.bettler.model.Cards._
import de.htwg.se.bettler.model._
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

class SwingGui(controller: Controller) extends Frame{
    var cardsSelected = Set.empty[Card]
    listenTo(controller)
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
    
    
        contents += new MenuItem(Action("Quit") {System.exit(0)})
        }
    }
    visible = true
    centerOnScreen()
    redraw
    reactions += {
        case event : GameChanged => redraw
    }


    def buttonPanel: GridPanel = new GridPanel(1,4):
        val playButton = new Button("Play")
        val skipButton = new Button("Skip")
        val undoButton = new Button("Undo")
        val redoButton = new Button("Redo")
        val input = new TextArea("") 
        
        contents += input
        contents += playButton
        contents += skipButton 
        contents += undoButton 
        contents += redoButton
        listenTo(input)       
        listenTo(playButton)
        listenTo(skipButton)
        listenTo(undoButton)
        listenTo(redoButton)

        reactions +={
            case ButtonClicked(`playButton`) => 
            
            val s = input.text.split(" ")
            var l = Set.empty[Card]
                for (i <- 0 to s.size - 1)
                    Card(s(i)) match
                        case Success(c) => 
                                l = l + c
                        case Failure(f) => println(f.getMessage)   
                controller.doAndNotify(controller.play, Cards(l))

            case ButtonClicked(`skipButton`) => controller.doAndNotify(controller.skip)
            case ButtonClicked(`undoButton`) => controller.undo
            case ButtonClicked(`redoButton`) => controller.redo
        }

    
    def showCards(cards : Cards): BoxPanel = new BoxPanel(Orientation.Horizontal):
        for(card <- cards.returnSet)
            var f = card.image
            var pic = ImageIO.read(f).getScaledInstance(52,80,java.awt.Image.SCALE_SMOOTH)
            val cb = new CheckBox(card.toString)
            cb.selectedIcon = ImageIcon(pic)
            cb.disabledIcon = ImageIcon(ImageIO.read(f).getScaledInstance(46,72,java.awt.Image.SCALE_SMOOTH))
            cb.icon = cb.disabledIcon
            cb.selected = false
            contents += cb
            listenTo(cb)
            reactions += {
                case SelectionChanged(`cb`) =>
                    if cb.selected then
                        Card(cb.text) match
                            case Success(s) => cardsSelected = cardsSelected + s
                            case Failure(f) => System.exit(0)
                        cb.icon = cb.selectedIcon
                    else 
                        Card(cb.text) match
                            case Success(s) => cardsSelected = cardsSelected - s
                        cb.icon = cb.disabledIcon
            }

    def mainMenuPanel : BoxPanel = new BoxPanel(Orientation.Horizontal):
        val startButton = new Button("Start Game")
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
        if !controller.game.isDefined then
            contents = new GridPanel(5,1):
                contents += mainMenuPanel
            return
        contents = new GridPanel(5,1):
            contents += new Label(controller.game.get.getMessage())
            contents += showCards(controller.game.get.getPlayers()(1))  
            contents += showCards(controller.game.get.getBoard())
            contents += showCards(controller.game.get.getPlayers()(0))
            contents += buttonPanel
        repaint()
}
