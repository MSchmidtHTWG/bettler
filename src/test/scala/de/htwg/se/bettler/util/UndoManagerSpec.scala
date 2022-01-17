package de.htwg.se.bettler
package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model._

class UndoManagerSpec extends AnyWordSpec {
    "An UndoManager" should {
        "have a method to undo a command" in {
            GameStateContext.setState(StartState())
            val undomanager = UndoManager()
            val game = Game("pvp")
            val controller = Controller(game)
            undomanager.doStep(PlayCommand(controller))
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            controller.doAndNotify(controller.skip)
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            undomanager.undoStep
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
        }
        "have a method to redo a command" in {
            GameStateContext.setState(StartState())
            val undomanager = UndoManager()
            val game = Game("pvp")
            val controller = Controller(game)
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
}