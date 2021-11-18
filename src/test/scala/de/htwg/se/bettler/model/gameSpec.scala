package de.htwg.se.bettler
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class gameSpec extends AnyWordSpec {
    "Game" should {
        "should create a new Game with 2 players, each with 7 Cards and an empty field" in {
            val game = Game()
            game.deck.size shouldBe(18)
            game.spieler1.cards.size shouldBe(7)
            game.spieler2.cards.size shouldBe(7)
            game.spielfeld.cards.size shouldBe(0)
            game.msg shouldBe("Spieler 1 ist dran.")
            game.state.isInstanceOf[P1TurnState] shouldBe(true)
        }
        "should have a method to play cards" in {
            val game = Game()
            val cards = Set(game.spieler1.cards.head)
            val game2 = game.play(cards)
            game2.spieler1.cards.size shouldBe(6)
            game2.spieler1.cards.size shouldBe(7)
            game2.state.isInstanceOf[P2TurnState] shouldBe(true)
            game2.spielfeld.cards.size shouldBe(1)
            game2.spielfeld.cards.contains(game.spieler1.cards.head) shouldBe(true)
            game2.spieler1.cards.contains(game.spieler1.cards.head) shouldBe(false)
            game2.msg shouldBe("Spieler 2 ist dran.")
            game.deck.size == game2.deck.size shouldBe(true)
        }
        "should have a method to skip turns" in {
            val game = Game()
            game.state.isInstanceOf[P1TurnState] shouldBe(true)
            val game2 = game.skip()
            game2.state.isInstanceOf[P2TurnState] shouldBe(true)
            val game3 = game2.skip()
            game3.state.isInstanceOf[P1TurnState] shouldBe(true)
        }
        "should have a String representation of the Game" in {
            val game = Game()
            val s = game.toString()
            val field = Field(game)
            s == field.toString + field.eol + game.msg shouldBe(true)
            val game2 = Game(P1TurnState(), Cards(Set(Card(Symbol.Clubs, Value.Ace))), Cards(Set(Card(Symbol.Diamonds, Value.Ace))), Cards(Set(Card(Symbol.Hearts, Value.Ace))), Deck(32), "test")
            game2.toString() shouldBe (
                "--------------------------------------------------" + field.eol +
                "Spieler 1" + field.eol +
                "[CA]" + field.eol +
                "--------------------------------------------------" + field.eol +
                "Spieler 2" + field.eol +
                "[DA]" + field.eol +
                "--------------------------------------------------" + field.eol +
                "[HA]" + field.eol +
                "--------------------------------------------------" + field.eol +
                field.eol + "test"
            )
        }
    }
}