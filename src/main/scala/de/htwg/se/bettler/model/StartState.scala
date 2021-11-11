package de.htwg.se.bettler
package model

class StartState(game : Game) extends State:
    var msg = ""
    override def toString = msg
    override def handle(input : String) : Unit =
        if (input == "start") {
            game.spielfeld = Set.empty[Card]
            game.spieler1 = game.deck.draw()
            game.spieler2 = game.deck.draw()
            game.state = P1TurnState(game)
            msg = "Neue Runde gestartet. Spieler 1 ist an der Reihe"
        } else {
            msg = "Falscher Input. Starte das Spiel mit Eingabe 'start'"
        }