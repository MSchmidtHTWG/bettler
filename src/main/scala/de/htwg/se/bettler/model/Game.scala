package de.htwg.se.bettler
package model

case class Game(state : State, spieler1 : Cards, spieler2 : Cards, spielfeld : Cards, deck : Deck, msg : String):
    def play(cards : Set[Card]) : Game =
        return state.play(cards, this)

    def skip() : Game = {
        if state.isInstanceOf[P1TurnState] then {
            val s = P2TurnState()
            val m = "Spieler 2 ist dran."
            copy(state = s, msg = m) 
        } else {
            val s = P1TurnState()
            val m = "Spieler 1 ist dran."
            copy(state = s, msg = m) 
        }
    }
    override def toString : String = {
        val field = Field(this)
        return field.printField() + field.eol + msg
    }

object Game:
        def apply() : Game =
            val d = Deck(32)
            val board = Cards(Set.empty[Card])
            val s1 = Cards(d.draw())
            val s2 = Cards(d.draw())
            val st = P1TurnState()
            return Game(st, s1, s2, board, d, "Spieler 1 ist dran.")
    

