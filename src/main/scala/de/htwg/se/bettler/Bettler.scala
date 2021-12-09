package de.htwg.se.bettler


import controller.Controller
import model._
import aview.TUI
import aview.gui._

@main def Main: Unit =
  val game = PvPGame()
  val controller = Controller(Some(game))
  val tui = TUI(controller)
  val gui = SwingGui(controller)
  tui.run
