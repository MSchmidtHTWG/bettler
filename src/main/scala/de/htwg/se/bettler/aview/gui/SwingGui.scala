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

class SwingGui(controller: Controller) extends Frame{
    
    listenTo(controller)
    title = "HTWG-Bettler"
    menuBar = new MenuBar {
    contents += new Menu("Option") {
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
            controller.doAndNotify(controller.newGame, "pvp")
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
        contents += playButton
        contents += skipButton 
        contents += undoButton 
        contents += redoButton
        listenTo(playButton)
        listenTo(skipButton)
        listenTo(undoButton)
        listenTo(redoButton)

        reactions +={
            //case ButtonClicked(playButton) =>
            case ButtonClicked(skipButton) => controller.doAndNotify(controller.skip)
            case ButtonClicked(undoButton) => controller.undo
            case ButtonClicked(redoButton) => controller.redo
        }

    
    def showCards(cards : Cards): BoxPanel = new BoxPanel(Orientation.Horizontal):
        var pics: ListBuffer[Image] = ListBuffer()
        for(card <- cards.returnSet)
            var f = card.image
            pics.addOne(ImageIO.read(f).getScaledInstance(52,80,java.awt.Image.SCALE_SMOOTH))

        override def paintComponent(g: java.awt.Graphics2D) =  {
            super.paintComponent(g)
            g.setColor(Color.DARK_GRAY)
            g.fillRect(0,0,1300,140)
            var x = 30
            var i = 0
            g.setColor(Color.WHITE)
            for(pic <- pics)
                g.drawImage(pic,x,10,null)
                g.drawString(i.toString,x + 20, 110)
                x = x + 60
                i = i + 1
        }

    def mainMenuPanel : BoxPanel = new BoxPanel(Orientation.Horizontal):
        val startButton = new Button("Start Game")
        contents += startButton  
        listenTo(startButton)
        reactions += {
            case ButtonClicked(`startButton`) => controller.doAndNotify(controller.newGame, "pvp")
        }

    //def cardPanel(cards : Cards): GridPanel  = new GridPanel(1,1):
        

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
