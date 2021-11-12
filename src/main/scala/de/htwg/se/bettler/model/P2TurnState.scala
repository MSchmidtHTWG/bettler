package de.htwg.se.bettler
package model


class P2TurnState() extends State:
    var msg = "Spieler 2 ist an der Reihe."
    override def toString = msg
    override def play(cards : Set[Card]) : Unit =
        if cards.isEmpty then {
            msg = "Falsche Eingabe. Spiele Karten mit 'play Karte1 Karte2 ..'. Spieler 2 ist an der Reihe."
            return
        }
        if !Card.isPartOfSet(cards, game.spieler2) then {
            msg = "Spieler 2 hat diese Karten nicht."
            return
        }
        if !Card.isPlayAble(cards) then {
            msg = "Die Karten haben nicht den gleichen Wert."
            return
        }
        if !Card.isBetter(cards, game.spielfeld) then {
            msg = "Es müssen genau gleichviele Karten gespielt wie auf dem Spielfeld liegen und ihr Wert muss größer sein."
            return
        }
        game.spieler2 = game.spieler2 -- cards
        game.spielfeld = cards
        game.state = P1TurnState(game)