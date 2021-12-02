package de.htwg.se.bettler
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class gameSpec extends AnyWordSpec {
    "A Game" should {
        "create a new Game with 2 players, each with 7 Cards and an empty field" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            game.getPlayers().size shouldBe(2)
            game.getBoard().cards.isEmpty shouldBe(true)
            for (p <- game.getPlayers()) {
                p.cards.size shouldBe(7)
            }
        }
        "have a method to play cards" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            GameStateContext.handle(Event.Start)
            val player1 = game.getPlayers()(0)
            val cards = Cards(Set(player1.returnSet.head))
            val game2 = game.play(cards)
            game2.getPlayers()(0).cards.size shouldBe(6)
            game2.getPlayers()(1).cards.size shouldBe(7)
            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            game2.getBoard().cards.size shouldBe(1)
            game2.getBoard().contains(cards) shouldBe(true)
            game2.getPlayers()(0).contains(cards) shouldBe(false)
            game2.getMessage() shouldBe("Player 2 turn.")
        }
        "have a method to skip turns" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            GameStateContext.handle(Event.Start)
            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            val game2 = game.skip()
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            val game3 = game.skip()
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
        }
        "have a String representation of the Game" in {
            /*val game = Game()
            val s = game.toString()
            val field = Field(game)
            s == (field.printField() + field.eol + game.msg) shouldBe(true)
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
            )*/
        }
    }
}