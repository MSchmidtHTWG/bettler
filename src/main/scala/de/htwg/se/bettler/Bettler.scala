package de.htwg.se.bettler

import controller.Controller
import model.Game
import aview.TUI

@main def Main: Unit =
  val game = Game.start()
  val controller = Controller(game)
  val tui = TUI(controller)
  tui.run
