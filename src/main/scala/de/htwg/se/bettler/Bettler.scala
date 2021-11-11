package de.htwg.se.bettler

import controller.Controller
import model.Game
import aview.TUI

@main def Main: Unit =
  println("Willkommen zu Bettler. Tippe 'start' ein um das Spiel zu starten.")
  val game = new Game
  val controller = Controller(game)
  val tui = TUI(controller)
  tui.run
  /*while (game.getGamestate() != "exit") {
    game.handle(StdIn.readLine());
  }*/
