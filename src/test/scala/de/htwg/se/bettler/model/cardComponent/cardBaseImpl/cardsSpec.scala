package de.htwg.se.bettler
package model
package cardComponent
package cardBaseImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

/*class cardsSpec extends AnyWordSpec {
    "Cards" should {
        "have a method to return the Set of Cards (Set[Card])" in {

            val set = Set[CardInterface(CardSymbol.Hearts, Value.Ace)]
            val cards = Cards(set)
            cards.returnSet.equals(set) shouldBe(true)
        }
        "have a method to check if a Card is contained in the Set of Cards" in {
            val set = Cards(Set(Card(Symbol.Hearts, Value.Ace)))
            val set2 = Cards(Set(Card(Symbol.Clubs, Value.Ace)))
            set.contains(set) shouldBe(true)
            set.contains(set2) shouldBe(false)
            set.contains(set) shouldBe(true)
            set.contains(set2) shouldBe(false)
        }
        "have a method to check if the Set of Cards is worse than another Set of Cards" in {
            val set = Cards(Set(Card(Symbol.Hearts, Value.Ace)))
            val set2 = Cards(Set(Card(Symbol.Clubs, Value.Ace)))
            val set3 = Cards(Set(Card(Symbol.Clubs, Value.Seven)))
            val cards = set
            val cards2 = Cards(Set.empty[CardInterface])
            val cards3 = set3
            cards.isWorse(set) shouldBe(false)
            cards.isWorse(set2) shouldBe(false)
            cards.isWorse(set3) shouldBe(false)
            cards.isWorse(cards2) shouldBe(false)
            cards2.isWorse(cards2) shouldBe(false)
            cards2.isWorse(set) shouldBe(true)
            cards2.isWorse(set2) shouldBe(true)
            cards2.isWorse(set3) shouldBe(true)
            cards.isWorse(set) shouldBe(false)
            cards.isWorse(set2) shouldBe(false)
            cards.isWorse(set3) shouldBe(false)
            cards.isWorse(Cards(Set.empty[CardInterface])) shouldBe(false)
            cards2.isWorse(Cards(Set.empty[CardInterface])) shouldBe(false)
            cards2.isWorse(set) shouldBe(true)
            cards2.isWorse(set2) shouldBe(true)
            cards2.isWorse(set2) shouldBe(true)
            cards3.isWorse(set) shouldBe(true)
            cards3.isWorse(cards) shouldBe(true)
        }
        "have a method to check if all the Cards in the Set have the same value" in {
            val set = Set(Card(Symbol.Hearts, Value.Ace), Card(Symbol.Diamonds, Value.Ace))
            val set2 = Set(Card(Symbol.Hearts, Value.Ace), Card(Symbol.Diamonds, Value.King))
            val cards = Cards(set)
            val cards2 = Cards(set2)
            val cards3 = Cards(Set.empty[Card])
            cards.isPlayable shouldBe(true)
            cards2.isPlayable shouldBe(false)
            cards3.isPlayable shouldBe(false)
        }
        "have a method to group the cards by value in a vector" in {
            val card1 = Card("H8")
            val card2 = Card("C8")
            val card3 = Card("H9")
            val cards = Cards(Set(card1.get, card2.get, card3.get))
            val cardGroupVector = cards.groupBySameValue
            cardGroupVector.size shouldBe(2)
            cardGroupVector(0).contains(Cards(Set(card1.get))) shouldBe(true)
            cardGroupVector(0).contains(Cards(Set(card2.get))) shouldBe(true)
            cardGroupVector(0).contains(Cards(Set(card3.get))) shouldBe(false)
            cardGroupVector(1).size shouldBe(1)
            cardGroupVector(1).contains(Cards(Set(card3.get))) shouldBe(true)
        }
        "have a method to find a playable card" in {
            val card1 = Card("H8")
            val card2 = Card("C8")
            val card3 = Card("H9")
            val card4 = Card("C7")
            val hand1 = Cards(Set(card1.get, card2.get))
            val hand2 = Cards(Set(card3.get))
            val board = Cards(Set(card4.get))
            val board2 = Cards(Set(card3.get))
            val h1p = hand1.findPlayable(board)
            h1p.isDefined shouldBe(true)
            h1p.get.size shouldBe(1)
            val h2p = hand2.findPlayable(board)
            h2p.isDefined shouldBe(true)
            h2p.get.size shouldBe(1)
            val h3p = hand1.findPlayable(board2)
            h3p.isDefined shouldBe(false)
        }
    }
}*/