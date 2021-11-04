package scala

import scala.util.Random
import scala.io.StdIn

val game = new Handler;
@main def Main: Unit =
  println("Willkommen zu Bettler. Tippe 'start' ein um das Spiel zu starten.")
  while (game.getGamestate() != "exit") {
    game.handle(StdIn.readLine());
  }







 

  

