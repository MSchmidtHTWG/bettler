package de.htwg.se.bettler
package model
package gameComponent

import cardComponent.cardBaseImpl._
import pveGameImpl._
import pvpGameImpl._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.bettler.util.GameMemento
import de.htwg.se.bettler.model.stateComponent.GameStateContext
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.PlayerTurnState

/*class gameSpec extends AnyWordSpec {
    "A Game" should {
        "have a factory method to create a pve or pvp game" in {
            val pvpGame = Game("pvp")
            val pveGame = Game("pve")

            pvpGame.isInstanceOf[PvPGame] shouldBe(true)
            pveGame.isInstanceOf[PvEGame] shouldBe(true)

        }
        "create a new Game with 2 players, each with 7 Cards and an empty field" in {
            val game = PvPGame()
            game.getPlayers().size shouldBe(2)
            game.getBoard().returnSet.isEmpty shouldBe(true)
            for (p <- game.getPlayers()) {
                p.returnSet.size shouldBe(7)
            }
            val game2 = PvEGame()
            game2.getPlayers().size shouldBe(2)
            game2.getBoard().returnSet.isEmpty shouldBe(true)
            for (p <- game2.getPlayers()) {
                p.returnSet.size shouldBe(7)
            }
        }
        "have a method to play cards" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            GameStateContext.handle(Events.Start)
            val player1 = game.getPlayers()(0)
            val player2 = game.getPlayers()(1)
            val cards = Cards(Set(player1.returnSet.head))
            val cards2 = Cards(Set(player2.returnSet.head))
    
            val game2 = game.play(cards)
            game2.getPlayers()(0).cards.size shouldBe(6)
            game2.getPlayers()(1).cards.size shouldBe(7)
            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            game2.getBoard().cards.size shouldBe(1)
            game2.getBoard().contains(cards) shouldBe(true)
            game2.getPlayers()(0).contains(cards) shouldBe(false)
            game2.getMessage() shouldBe("Player 2 turn.")

            GameStateContext.setState(PlayerTurnState(0,2))
            val game3 = game.play(cards2)
            game3.getMessage() shouldBe("Cards are not playable.")

            GameStateContext.setState(StartState())
            val game4 = game.play(cards)
            game4.getMessage() shouldBe("It is not a players turn right now.")
        }
        "have a method to skip turns" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            GameStateContext.handle(Events.Start)
            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            val game2 = game.skip()
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            val game3 = game.skip()
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
        }
        "have a method to restore to a previous version from a memento" in {
            val game = PvPGame()
            val game2 = PvEGame()
            val state = StartState()
            val gameMemento = GameMemento(game2, state)
            val game3 = game.restore(gameMemento)
            game3 shouldBe(game2)
            GameStateContext.state shouldBe(state)
        }
        "have a method to save itself and the state in a memento" in {
            val state = StartState()
            val game = PvPGame()
            GameStateContext.setState(state)
            val memento = game.save()
            memento.state() shouldBe(state)
            memento.game() shouldBe(game)
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
}*/