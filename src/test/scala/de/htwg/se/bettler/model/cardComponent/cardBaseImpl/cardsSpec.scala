package de.htwg.se.bettler
package model
package cardComponent
package cardBaseImpl


import Value._
import Symbol._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class cardsSpec extends AnyWordSpec {
    "Cards" should {
        "have a method to return the Set of Cards (Set[Card])" in {

            val set = (Set[CardInterface](Card(Symbol.Hearts, Value.Ace)))
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
            val set = Set[CardInterface](Card(Symbol.Hearts, Value.Ace), Card(Symbol.Diamonds, Value.Ace))
            val set2 = Set[CardInterface](Card(Symbol.Hearts, Value.Ace), Card(Symbol.Diamonds, Value.King))
            val cards = Cards(set)
            val cards2 = Cards(set2)
            val cards3 = Cards(Set.empty[CardInterface])
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
            h1p.size shouldBe(1)
            val h2p = hand2.findPlayable(board)
            h2p.size shouldBe(1)
            val h3p = hand1.findPlayable(board2)
        }
        "Have a Method for adding  Cards to Cards" in {
            val set:CardsInterface = Cards(Set(Card(Symbol.Hearts, Value.Ace)))
            val set2 = Cards(Set(Card(Symbol.Clubs, Value.Ace)))
            val card1 = Cards(Set(Card(Symbol.Diamonds, Value.Eight)))
            val newCards = set.add(card1)
     

            set.contains(set2) shouldBe(false)
            newCards.contains(card1) shouldBe(true)

        
        }   
        "Have a Method for adding a Card to Cards" in {
            val set:CardsInterface = Cards(Set(Card(Symbol.Hearts, Value.Ace)))           
            val set2 = Cards(Set(Card(Symbol.Clubs, Value.Ace)))
            val card1 = Card(Symbol.Diamonds, Value.Eight)
            val card2 = Cards(Set(Card(Symbol.Diamonds, Value.Eight)))
            val newCards = set.add(card1)
     

            set.contains(set2) shouldBe(false)
            newCards.contains(card2) shouldBe(true)

        
        }  
        "Have a Method for removing Cards from Cards " in {
            val set:CardsInterface = Cards(Set(Card(Symbol.Hearts, Value.Ace)))
            val set2 = Cards(Set(Card(Symbol.Clubs, Value.Ace)))
            val card1 = Cards(Set(Card(Symbol.Diamonds, Value.Eight)))
            val newCards = set.add(card1)
            newCards.contains(card1) shouldBe(true)
            val removedCards = set.remove(card1)
            removedCards.contains(card1) shouldBe(false)


        }  
        "Have a Method for removing a Card from Cards " in {
            val set:CardsInterface = Cards(Set(Card(Symbol.Hearts, Value.Ace)))
            val set2 = Cards(Set(Card(Symbol.Clubs, Value.Ace)))
            val card1 = Card(Symbol.Diamonds, Value.Eight)
            val card2 = Cards(Set(Card(Symbol.Diamonds, Value.Eight)))
            val newCards = set.add(card1)
            newCards.contains(card2) shouldBe(true)
            val removedCards = set.remove(card1)
            removedCards.contains(card2) shouldBe(false)


        }     

        "Have a Method for finding the best Card" in {
            val set:CardsInterface = Cards(Set(Card(Symbol.Hearts, Value.Ace),Card(Symbol.Hearts, Value.Seven)))

            val set3 = set.bestCards
            set.contains(set3) shouldBe(true)
            set3.contains(Cards(Set(Card(Symbol.Hearts, Value.Ace)))) shouldBe(true)
        }

        "Have a Method for finding the worst Card" in {
            val set:CardsInterface = Cards(Set(Card(Symbol.Hearts, Value.Ace),Card(Symbol.Hearts, Value.Seven)))

            val set3 = set.worstCards
            set.contains(set3) shouldBe(true)
            set3.contains(Cards(Set(Card(Symbol.Hearts, Value.Seven)))) shouldBe(true)
        }

        "Have Method to convert Cards to Srting" in {
            val card1 = Cards(Set(Card(Symbol.Diamonds, Value.Eight)))
            val card2 = Cards(Set(Card(Symbol.Clubs, Value.Ace)))
            val card3 = Cards(Set(Card(Symbol.Spades, Value.King)))

            val card1s = card1.toString
            val card2s = card2.toString
            val card3s = card3.toString

            card1s shouldBe("D8")
            card2s shouldBe("CA")
            card3s shouldBe("SK")
        }



        
    }
}