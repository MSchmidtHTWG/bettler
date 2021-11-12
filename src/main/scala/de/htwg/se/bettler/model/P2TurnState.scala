package de.htwg.se.bettler
package model


class P2TurnState() extends State:
    override def play(cards : Set[Card], game : Game) : Game =
        if cards.isEmpty then {
            val msg = "Falsche Eingabe. Spiele Karten mit 'play Karte1 Karte2 ..'. Spieler 1 ist an der Reihe."
            return Game(game.state, game.spieler1, game.spieler2, game.spielfeld, game.deck, msg)
        }
        if game.spieler2.contains(cards) then {
            val msg = "Spieler 1 hat diese Karten nicht."
            return Game(game.state, game.spieler1, game.spieler2, game.spielfeld, game.deck, msg)
        }
        if Cards(cards).isPlayable then {
            val msg = "Die Karten haben nicht den gleichen Wert."
            return Game(game.state, game.spieler1, game.spieler2, game.spielfeld, game.deck, msg)
        }
        if game.spielfeld.isWorse(cards) then {
            val msg = "Es müssen genau gleichviele Karten gespielt wie auf dem Spielfeld liegen und ihr Wert muss größer sein."
            return Game(game.state, game.spieler1, game.spieler2, game.spielfeld, game.deck, msg)
        }
        val s1 = game.spieler2.returnSet -- cards
        val board = cards
        val state = P2TurnState()
        return Game(state, game.spieler1, Cards(s1), Cards(board), game.deck, "Spieler 1 ist dran.")