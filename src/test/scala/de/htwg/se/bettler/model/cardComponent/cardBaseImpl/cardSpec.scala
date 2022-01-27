package de.htwg.se.bettler
package model
package cardComponent
package cardBaseImpl



import Value._
import Symbol._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import java.io.File

class cardSpec extends AnyWordSpec:
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

    "have a method for creating a card from a string, failing if the string does not represent a card" in {
      Card("C7").get should be (Card(Symbol.Clubs,Value.Seven))
      Card("C8").get should be (Card(Symbol.Clubs,Value.Eight))
      Card("C9").get should be (Card(Symbol.Clubs,Value.Nine))
      Card("C10").get should be (Card(Symbol.Clubs,Value.Ten))
      Card("CJ").get should be (Card(Symbol.Clubs,Value.Jack))
      Card("CQ").get should be (Card(Symbol.Clubs,Value.Queen))
      Card("CK").get should be (Card(Symbol.Clubs,Value.King))
      Card("CA").get should be (Card(Symbol.Clubs,Value.Ace))
      Card("DA").get should be (Card(Symbol.Diamonds,Value.Ace))
      Card("SA").get should be (Card(Symbol.Spades,Value.Ace))
      Card("HA").get should be (Card(Symbol.Hearts,Value.Ace))
      Card("C11").isFailure should be (true)
      Card("Q7").isFailure should be (true)
      Card("").isFailure should be (true)
    }
    "have a method for finding out if a card is higher in value than another card" in {
      card1.isHigher(card2) shouldBe(false)
      card2.isHigher(card1) shouldBe(true)
      card2.isHigher(card3) shouldBe(true)
      card1.isHigher(card1) shouldBe(false)
    }
    "have a method for finding out if the value of two cards is the same" in {
      card2.sameValue(card1) shouldBe(false)
      card1.sameValue(card2) shouldBe(false)
      card2.sameValue(card2) shouldBe(true)
      card1.sameValue(card1) shouldBe(true)
      card1.sameValue(card4) shouldBe(true)
      card4.sameValue(card1) shouldBe(true)
    }
    "have a string representation of a card" in {
      card1.toString() shouldBe("C7")
      card2.toString() shouldBe("SK")
      card3.toString() shouldBe("DQ")
      card4.toString() shouldBe("H7")
    }
    "have a method for returning the value of a card" in {
      card1.getValue shouldBe(Value.Seven)
      card2.getValue shouldBe(Value.King)
      card3.getValue shouldBe(Value.Queen)
      card4.getValue shouldBe(Value.Seven)

    }
    "have a method for getting the symbol of a card" in {
      card1.getSymbol shouldBe(Symbol.Clubs)
      card2.getSymbol shouldBe(Symbol.Spades)
      card3.getSymbol shouldBe(Symbol.Diamonds)
      card4.getSymbol shouldBe(Symbol.Hearts)

    }
    "have a method to return the corresponding number for ordering of a card's value" in {
      card1.intValue should be(7)
    }
    "have a method to return itself as cards holding a set containing only itself" in {
      val cards = card1.toCards
      cards.cards.contains(card1) should be(true)
      cards.size should be(1)
    }
    "have a method to return a file containing a picture to represent the card" in {
      val file = card1.image
      file.isInstanceOf[File] should be(true)
    }
  }