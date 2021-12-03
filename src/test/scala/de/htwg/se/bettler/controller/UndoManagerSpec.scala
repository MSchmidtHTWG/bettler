package de.htwg.se.bettler
package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model._

class UndoManagerSpec extends AnyWordSpec {
    "An UndoManager" should {
        "have a method to undo a command" in {
            val undomanager = UndoManager()
            val game = Game("pvp")
            undomanager.doStep(PlayCommand(Controller(game)))
            val controller = Controller(game)
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(0)
            controller.doAndNotify(controller.skip)
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
            controller.undo
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer shouldBe(1)
        }
    }
}