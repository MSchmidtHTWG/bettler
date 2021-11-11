package de.htwg.se.bettler

import controller.Controller
import aview.TUI

@main def Main: Unit =
  println("Willkommen zu Bettler. Tippe 'start' ein um das Spiel zu starten.")
  val controller = Controller(field)
  val tui = TUI(controller)
  tui.run
  /*while (game.getGamestate() != "exit") {
    game.handle(StdIn.readLine());
  }*/
