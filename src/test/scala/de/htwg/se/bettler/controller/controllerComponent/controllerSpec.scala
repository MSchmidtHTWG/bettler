package de.htwg.se.bettler
package controller
package controllerBaseImp

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model._
import de.htwg.se.bettler.model.stateComponent.GameStateContext
import de.htwg.se.bettler.model.gameComponent.pvpGameImpl.PvPGame
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.StartState
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl.Cards
import de.htwg.se.bettler.model.stateComponent.GameStateEvents
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.PlayerTurnState
import de.htwg.se.bettler.model.gameComponent.pveGameImpl.PvEGame
import de.htwg.se.bettler.model.gameComponent.Game

class controllerSpec extends AnyWordSpec {
    "Controller" should {
        GameStateContext.setState(StartState())
        val game = PvPGame()
        val controller = Controller(Some(game))
        val player1 = game.getPlayers()(0)
        val player1HeadCard = Cards(Set(player1.returnSet.head))
        "have a method play, that returns a Game" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            GameStateContext.handle(GameStateEvents.Start)
            val controller = Controller(Some(game))
            val player1 = game.getPlayers()(0)
            val player1HeadCard = Cards(Set(player1.returnSet.head))

            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            controller.game match
                case Some(g) => g.getMessage() shouldBe("Player 1 turn.")
            controller.doAndNotify(controller.play, player1HeadCard)
            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            controller.game match
                case Some(g) => g.getMessage() shouldBe("Player 2 turn.")
        }
        "have a method skip to skip a turn" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            GameStateContext.handle(GameStateEvents.Start)
            val controller = Controller(Some(game))

            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            controller.doAndNotify(controller.skip)
            GameStateContext.getState().isInstanceOf[PlayerTurnState] shouldBe(true)
            GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
        }
        "have a method start to start a new game" in {
            GameStateContext.setState(StartState())
            val game = PvPGame()
            val controller = Controller(Some(game))

            controller.doAndNotify(controller.newGame, "pve")
            controller.game.get.isInstanceOf[PvEGame] shouldBe(true)
        }
        "have a method toString that returns the games string representation or a message, that the game is not initialized" in {
            Controller(None).toString shouldBe("Currently no game running.")
            val game = Game("pvp")
            Controller(Some(game)).toString.equals(game.toString) should be(true)
        }
        "have a method to save a game and to restore the game from a saved game" in {
            val game = Game("pvp")
            val controller = Controller(Some(game))
            controller.stack.isEmpty shouldBe(true)
            controller.addMemento()
            controller.stack.isEmpty shouldBe(false)
            controller.doAndNotify(controller.newGame, "pve")
            controller.restore
            controller.game.get should be(game)
        }
    }
}