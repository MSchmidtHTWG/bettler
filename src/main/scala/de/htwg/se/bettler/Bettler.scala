package de.htwg.se.bettler

import controller.Controller
import model.Game
import aview.TUI

@main def Main: Unit =
  val game = new Game
  val controller = Controller(game)
  val tui = TUI(controller)
  tui.run
  /*while (game.getGamestate() != "exit") {
    game.handle(StdIn.readLine());
  }*/
