package de.htwg.se.bettler

import controller.Controller
import model._
import aview.TUI

@main def Main: Unit =
  val game = PvPGame()
  val controller = Controller(game)
  val tui = TUI(controller)
  tui.run
