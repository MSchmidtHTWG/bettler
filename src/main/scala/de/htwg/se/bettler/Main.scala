package scala

import scala.util.Random

val game = new Handler;
@main def Main: Unit =
  game.handle("Start")
  while (game.getGamestate() != "Exit") {
    game.handle("Exit");
    println("Main hat game.handle mit Exit aufgerufen")
  }
  println("Main hat while verlassen")







 

  

