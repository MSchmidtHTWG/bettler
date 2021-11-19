package de.htwg.se.bettler
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class cardsSpec extends AnyWordSpec {
    "Cards" should {
        "have a method to return the Set of Cards (Set[Card])" in {
            val set = Set(Card(Symbol.Hearts, Value.Ace))
            val cards = Cards(set)
            cards.returnSet.equals(set) shouldBe(true)
        }
        "have a method to check if a Card is contained in the Set of Cards" in {
            val set = Set(Card(Symbol.Hearts, Value.Ace))
            val set2 = Set(Card(Symbol.Clubs, Value.Ace))
            val cards = Cards(set)
            cards.contains(set) shouldBe(true)
            cards.contains(set2) shouldBe(false)
            cards.contains(Cards(set)) shouldBe(true)
            cards.contains(Cards(set2)) shouldBe(false)
        }
        "have a method to check if the Set of Cards is worse than another Set of Cards" in {
            val set = Set(Card(Symbol.Hearts, Value.Ace))
            val set2 = Set(Card(Symbol.Clubs, Value.Ace))
            val set3 = Set(Card(Symbol.Clubs, Value.Seven))
            val cards = Cards(set)
            val cards2 = Cards(Set.empty[Card])
            val cards3 = Cards(set3)
            cards.isWorse(set) shouldBe(false)
            cards.isWorse(set2) shouldBe(false)
            cards.isWorse(set3) shouldBe(false)
            cards.isWorse(Set.empty[Card]) shouldBe(false)
            cards2.isWorse(Set.empty[Card]) shouldBe(false)
            cards2.isWorse(set) shouldBe(true)
            cards2.isWorse(set2) shouldBe(true)
            cards2.isWorse(set3) shouldBe(true)
            cards.isWorse(Cards(set)) shouldBe(false)
            cards.isWorse(Cards(set2)) shouldBe(false)
            cards.isWorse(Cards(set3)) shouldBe(false)
            cards.isWorse(Cards(Set.empty[Card])) shouldBe(false)
            cards2.isWorse(Cards(Set.empty[Card])) shouldBe(false)
            cards2.isWorse(Cards(set)) shouldBe(true)
            cards2.isWorse(Cards(set2)) shouldBe(true)
            cards2.isWorse(Cards(set3)) shouldBe(true)
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
    }
}