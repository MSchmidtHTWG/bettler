package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FinishedStateSpec extends AnyWordSpec with Matchers:
    "A startstate" should {
        "return a playerturnstate with the loser set to have his turn when called to handle a start event" in {
            val newState = FinishedState(0,1).handle(GameStateEvents.Start).asInstanceOf[PlayerTurnState].currentPlayer should be(1)
        }
        "return itself when called to handle any other event" in {
            FinishedState(0,1).isInstanceOf[FinishedState] should be(true)
        }
    }