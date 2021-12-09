package de.htwg.se.bettler.aview.gui
import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.bettler.controller._
import scala.io.Source._

class SwingGui {
    class SwingGui(controller: Controller) extends Frame {

        listenTo(controller)
        title = "HTWG Bettler"

    }
  
}
