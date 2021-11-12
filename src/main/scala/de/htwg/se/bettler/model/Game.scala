package de.htwg.se.bettler
package model

class Game:
    var state : State = null
    var spieler1 = Set.empty[Card]
    var spieler2 = Set.empty[Card]
    var spielfeld = Set.empty[Card]
    val deck = Deck()

    def start() : Unit =
        spielfeld = Set.empty[Card]
        spieler1 = deck.draw()
        spieler2 = deck.draw()
        state = P1TurnState(this)
    def handle(input : String) : Unit =
        state.handle(input)
    def play(cards : Set[Card]) : Unit =
        state.play(cards)
    def skip() : Unit =
        if state == null then return
        if state.isInstanceOf[P1TurnState] then {
            state = P2TurnState(this)
        } else {
            state = P1TurnState(this)
        }
        spielfeld = Set.empty[Card]

    override def toString = Field.printField(spielfeld, spieler1, spieler2) + Field.eol + state.toString
    

