package de.htwg.se.bettler
package model

class Game:
    var state : State = StartState(this)
    var spieler1 = Set.empty[Card]
    var spieler2 = Set.empty[Card]
    var spielfeld = Set.empty[Card]
    val deck = Deck()

    def handle(input : String) : Unit =
        state.handle(input)
        
    override def toString = field.printField(spielfeld, spieler1, spieler2) + field.eol + state.toString
    

