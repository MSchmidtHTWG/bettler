package de.htwg.se.bettler
package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model._

class controllerSpec extends AnyWordSpec {
    "Controller" should {
        GameStateContext.setState(StartState())
        val game = PvPGame()
        val controller = Controller(game)
        val player1 = game.getPlayers()(0)
        val player1HeadCard = Cards(Set(player1.returnSet.head))
        "have a method play, that returns a Game" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            val controller = Controller(game)
            val player1 = game.getPlayers()(0)
            val player1HeadCard = Cards(Set(player1.returnSet.head))

            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            controller.game.getMessage() shouldBe("Player 1 turn.")
            controller.doAndNotify(controller.play, player1HeadCard)
            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            controller.game.getMessage() shouldBe("Player 2 turn.")
        }
        "have a method skip to skip a turn" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            val controller = Controller(game)

            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            controller.doAndNotify(controller.skip)
            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
        }
        "have a method start to start a new game" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            val controller = Controller(game)

            controller.doAndNotify(controller.newGame, "pve")
            controller.game.isInstanceOf[PvEGame] shouldBe(true)
        }
    }
}