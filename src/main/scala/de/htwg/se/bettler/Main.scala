package scala

import scala.util.Random

@main def Main: Unit =
  val deck = new deck
  deck.shuffle();
  var spieler1 = deck.draw();
  var spieler2 = deck.draw();
  val field = new field
  field.printField()
  println(spieler1)
  println(spieler2)







 

  

