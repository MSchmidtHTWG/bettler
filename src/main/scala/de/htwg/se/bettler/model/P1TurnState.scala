package de.htwg.se.bettler
package model

class P1TurnState() extends State:
    var msg = "Spieler 1 ist an der Reihe."
    override def toString = msg
    override def play(cards : Set[Card], game : Game) : Game =
        if cards.isEmpty then {
            msg = "Falsche Eingabe. Spiele Karten mit 'play Karte1 Karte2 ..'. Spieler 1 ist an der Reihe."
            return
        }
        if !Card.isPartOfSet(cards, game.spieler1) then {
            msg = "Spieler 1 hat diese Karten nicht."
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
        val s1 = game.spieler1 -- cards
        val board = cards
        val state = P2TurnState()
        return Game(state, s1, game.spieler2, board, game.deck)