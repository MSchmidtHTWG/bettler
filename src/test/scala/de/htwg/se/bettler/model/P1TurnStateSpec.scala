package de.htwg.se.bettler
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class P1TurnStateSpec extends AnyWordSpec {
    "P1TurnStateSpec" should {
        "have a method play, which takes Cards and a Game and returns a new Game with updated parameters" in {
            val game = Game()
            val state = P2TurnState()
            val set = Set(Card(Symbol.Hearts, Value.Seven), Card(Symbol.Hearts, Value.Eight))
            val set2 = Set(Card(Symbol.Diamonds, Value.Ten), Card(Symbol.Hearts, Value.Ten))
            val set4 = Set(Card(Symbol.Diamonds, Value.Ten), Card(Symbol.Hearts, Value.Ace))
            val cards = game.spieler1.returnSet.head
            val game2 = state.play(Set.empty[Card], game)
            game2.spieler1.equals(game.spieler1) shouldBe(true)
            game2.spieler2.equals(game.spieler2) shouldBe(true)
            game2.deck.equals(game.deck) shouldBe(true)
            game2.spielfeld.equals(game.spielfeld) shouldBe(true)
            game2.state.equals(game.state) shouldBe(true)
            game2.msg == "Falsche Eingabe. Spiele Karten mit 'play Karte1 Karte2 ..'. Spieler 1 ist an der Reihe."

            val game3 = state.play(Set(game.spieler1.cards.head), game)
            game3.spieler1.equals(game.spieler1) shouldBe(true)
            game3.spieler2.equals(game.spieler2) shouldBe(true)
            game3.deck.equals(game.deck) shouldBe(true)
            game3.spielfeld.equals(game.spielfeld) shouldBe(true)
            game3.state.equals(game.state) shouldBe(true)
            game3.msg == "Spieler 2 hat diese Karten nicht."

            val game5 = Game(state, Cards(set), Cards(set4), Cards(Set.empty[Card]), Deck(32), "")
            val game4 = state.play(game5.spieler2.cards, game5)
            game4.spieler1.equals(game5.spieler1) shouldBe(true)
            game4.spieler2.equals(game5.spieler2) shouldBe(true)
            game4.deck.equals(game5.deck) shouldBe(true)
            game4.spielfeld.equals(game5.spielfeld) shouldBe(true)
            game4.state.equals(game5.state) shouldBe(true)
            game4.msg == "Die Karten haben nicht den gleichen Wert."

            val set3 = Set(game5.spieler2.cards.head)
            val game6 = state.play(set3, game5)
            game6.state.isInstanceOf[P1TurnState] shouldBe(true)
            game6.spieler2.contains(set3) shouldBe(false)
            game6.spielfeld.contains(set3) shouldBe(true)
            game6.msg == "Spieler 1 ist dran." shouldBe(true)

            val game7 = Game(P1TurnState(), Cards(Set.empty[Card]),Cards(Set(Card(Symbol.Clubs, Value.Ace))), Cards(set2), Deck(32), "")
            val game8 = state.play(game7.spieler2.cards, game7)
            game8.msg == "Es müssen genau gleichviele Karten gespielt wie auf dem Spielfeld liegen und ihr Wert muss größer sein." shouldBe(true)
        }
    }
}