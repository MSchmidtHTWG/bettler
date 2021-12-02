package de.htwg.se.bettler
package model

import Card._

import Value._
import Symbol._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class cardSpec extends AnyWordSpec{
  "Card" should {
    val card1 = Card(Symbol.Clubs, Value.Seven)
    val card2 = Card(Symbol.Spades, Value.King)
    val card3 = Card(Symbol.Diamonds, Value.Queen)
    val card4 = Card(Symbol.Hearts, Value.Seven)
    var set = Set.empty[Card]
    var set2 = Set.empty[Card]
    var set3 = Set.empty[Card]
    set = set + card1
    set = set + card2
    set2 = set2 + card3
    set3 = set3 + card2

    "have A Method for creating and returning Cards"  in {
      Card("C7") should be (Some(Card(Symbol.Clubs,Value.Seven)))
      Card("C8") should be (Some(Card(Symbol.Clubs,Value.Eight)))
      Card("C9") should be (Some(Card(Symbol.Clubs,Value.Nine)))
      Card("C10") should be (Some(Card(Symbol.Clubs,Value.Ten)))
      Card("CJ") should be (Some(Card(Symbol.Clubs,Value.Jack)))
      Card("CQ") should be (Some(Card(Symbol.Clubs,Value.Queen)))
      Card("CK") should be (Some(Card(Symbol.Clubs,Value.King)))
      Card("CA") should be (Some(Card(Symbol.Clubs,Value.Ace)))
      Card("DA") should be (Some(Card(Symbol.Diamonds,Value.Ace)))
      Card("SA") should be (Some(Card(Symbol.Spades,Value.Ace)))
      Card("HA") should be (Some(Card(Symbol.Hearts,Value.Ace)))
      Card("C11") should be (None)
      Card("Q7") should be (None)
      Card("") shouldBe (None)
      val card = Card.apply("C7")
      card.isDefined shouldBe(true)
    }
    "have A Method for finding out the Higher Card" in {
      card1.isHigher(card2) shouldBe(false)
      card2.isHigher(card1) shouldBe(true)
      card2.isHigher(card3) shouldBe(true)
      card1.isHigher(card1) shouldBe(false)
    }
    "have A Method for finding out if the value is the same" in {
      card2.sameValue(card1) shouldBe(false)
      card1.sameValue(card2) shouldBe(false)
      card2.sameValue(card2) shouldBe(true)
      card1.sameValue(card1) shouldBe(true)
      card1.sameValue(card4) shouldBe(true)
      card4.sameValue(card1) shouldBe(true)
    }
    "have a string representation of a Card" in {
      card1.toString() shouldBe("C7")
      card2.toString() shouldBe("SK")
      card3.toString() shouldBe("DQ")
      card4.toString() shouldBe("H7")
    }
  }
}