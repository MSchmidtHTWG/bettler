package de.htwg.se.bettler
package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.stateComponent._
import model.stateComponent.stateBaseImpl._
import model.gameComponent._
import de.htwg.se.bettler.controller.controllerBaseImp._

class UndoManagerSpec extends AnyWordSpec:
    GameStateContext.setState(StartState())
    val undomanager = UndoManager()
    val game = Game("pvp")
    val controller = Controller(Some(game))
    "An UndoManager" should {
        "have a method to undo a command" in {
            undomanager.doStep(PlayCommand(controller))
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            controller.doAndNotify(controller.skip)
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            undomanager.undoStep
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
        }
        "have a method to redo a command" in {
            undomanager.doStep(PlayCommand(controller))
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            controller.doAndNotify(controller.skip)
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            undomanager.undoStep
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            undomanager.redoStep
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
        }
    }