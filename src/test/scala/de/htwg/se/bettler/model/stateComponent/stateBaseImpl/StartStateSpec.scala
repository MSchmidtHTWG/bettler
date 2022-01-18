package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StartStateSpec extends AnyWordSpec with Matchers:
    "A startstate" should {
        "return a playerturnstate with the first player set to have his turn when called to handle a start event" in {
            val newState = StartState().handle(GameStateEvents.Start)
            newState.isInstanceOf[PlayerTurnState] should be(true)
            newState.asInstanceOf[PlayerTurnState].currentPlayer should be(0)
        }
        "return itself when called to handle any other event" in {
            val newState = StartState().handle(GameStateEvents.Finished)
            newState.isInstanceOf[StartState] should be(true)
            val newState2 = StartState().handle(GameStateEvents.Skip)
            newState2.isInstanceOf[StartState] should be(true)
        }
    }