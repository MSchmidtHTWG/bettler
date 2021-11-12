package de.htwg.se.bettler
package model

case class Game(state : State, spieler1 : Cards, spieler2 : Cards, spielfeld : Cards, deck : Deck, msg : String):
    def start() : Game =
        val d = Deck()
        val board = Cards(Set.empty[Card])
        val s1 = Cards(d.draw())
        val s2 = Cards(d.draw())
        val st = P1TurnState()
        return Game(st, s1, s2, board, d, "Spieler 1 ist dran.")

    def play(cards : Set[Card]) : Game =
        return state.play(cards, this)

    def skip() : Game = {
        if state.isInstanceOf[P1TurnState] then {
            val s = P2TurnState()
            val m = "Spieler 2 ist dran."
            return Game(s, this.spieler1, this.spieler2, this.spielfeld, this.deck, m) 
        } else {
            val s = P1TurnState()
            val m = "Spieler 1 ist dran."
            return Game(s, this.spieler1, this.spieler2, this.spielfeld, this.deck, m)
        }
    }


    override def toString = Field.printField(spielfeld.cards, spieler1.cards, spieler2.cards) + Field.eol + msg
    

