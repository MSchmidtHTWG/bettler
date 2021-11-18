package de.htwg.se.bettler
package model

import Card._

import Value._
import Symbol._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class cardSpec extends AnyWordSpec{



   "Card" should {

    val card1 = new Card(Symbol.Clubs,Value.Seven)
    val card2 = new Card(Symbol.Spades,Value.King)
    val card3 = new Card(Symbol.Diamonds,Value.Queen)
    var set = Set.empty[Card]
    var set2 = Set.empty[Card]
    var set3 = Set.empty[Card]
    set = set + card1
    set = set + card2


    set2 = set2 + card3
    set3 = set3 + card2

    //deck = new Deck()
    "Should have A Method for returning Cards "  in {
      Card.returnCard("C7") should be (Card(Symbol.Clubs,Value.Seven))
      Card.returnCard("SK") should be (Card(Symbol.Spades,Value.King))
      Card.returnCard("DQ") should be (Card(Symbol.Diamonds,Value.Queen))
      Card.returnCard("NSD") should be (null)
}
    "Should have A Method for finding out the Higher Card "  in {
      Card.isBetter(set,set) should be (false)
      Card.isBetter(set3,set2) should be (true)

}
    "Should have A Method for finding out if a Card is part of a set "  in {
      Card.isPartOfSet(set3,set) should be (true)
      Card.isPartOfSet(set,set3) should be (false)
    
}
    "Should have A Method for finding out if a Card is playble"  in {
      Card.isPlayAble(set3) should be (true)
      Card.isPlayAble(set) should be (false)

}
    "Should have A Method for finding out if the value is the same"  in {
      card2.sameValue(card1) should be (false)
      card2.sameValue(card2) should be (true)

}
    "Should have A Method for finding out if the value of a Card is Higher"  in {
      card2.isHigher(card1) should be (true)
      card1.isHigher(card3) should be (false)
     

}








  }
}