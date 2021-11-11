package de.htwg.se.bettler
package model

class P1TurnState(game : Game) extends State:
    override def handle(input : String) : Unit =
        if (input == "start") {
            game.spielfeld = Set.empty[Card]
            game.spieler1 = game.deck.draw()
            game.spieler2 = game.deck.draw()
            game.state = P1TurnState(game)
        }