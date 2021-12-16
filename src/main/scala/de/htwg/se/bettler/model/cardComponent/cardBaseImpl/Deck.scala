package de.htwg.se.bettler
package model
package cardComponent
package cardBaseImpl

import scala.util.Random

case class Deck(size : Int) extends DeckInterface:
    var deck = Set.empty[Card]

    if size == 32 then {
        deck = Set(Card(Symbol.Hearts, Value.Seven), Card(Symbol.Hearts, Value.Eight), Card(Symbol.Hearts, Value.Nine), Card(Symbol.Hearts, Value.Ten), Card(Symbol.Hearts, Value.Jack), Card(Symbol.Hearts, Value.Queen), Card(Symbol.Hearts, Value.King), Card(Symbol.Hearts, Value.Ace))
        deck = deck + Card(Symbol.Diamonds, Value.Seven) + Card(Symbol.Diamonds, Value.Eight) + Card(Symbol.Diamonds, Value.Nine) + Card(Symbol.Diamonds, Value.Ten) + Card(Symbol.Diamonds, Value.Jack) + Card(Symbol.Diamonds, Value.Queen) + Card(Symbol.Diamonds, Value.King) + Card(Symbol.Diamonds, Value.Ace)
        deck = deck + Card(Symbol.Clubs, Value.Seven) + Card(Symbol.Clubs, Value.Eight) + Card(Symbol.Clubs, Value.Nine) + Card(Symbol.Clubs, Value.Ten) + Card(Symbol.Clubs, Value.Jack) + Card(Symbol.Clubs, Value.Queen) + Card(Symbol.Clubs, Value.King) + Card(Symbol.Clubs, Value.Ace)
        deck = deck + Card(Symbol.Spades, Value.Seven) + Card(Symbol.Spades, Value.Eight) + Card(Symbol.Spades, Value.Nine) + Card(Symbol.Spades, Value.Ten) + Card(Symbol.Spades, Value.Jack) + Card(Symbol.Spades, Value.Queen) + Card(Symbol.Spades, Value.King) + Card(Symbol.Spades, Value.Ace)
    }
    def draw() : Cards = 
        val ran = Random()
        //ran.setSeed(DeckStrategy.execute(DeckStrategy.strategy2))
        var l : List[Card] = deck.toList
        l = ran.shuffle(l).slice(0, 7)
        var r = Set.empty[Card]
        for (ll <- l) {
            r = r + ll
        }
        deck = deck -- r
        return Cards(r)