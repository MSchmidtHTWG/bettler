package de.htwg.se.bettler
package model
package stateComponent

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.bettler.model.stateComponent.GameStateContext
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.StartState

class GameStateContextSpec extends AnyWordSpec with Matchers:
    var handled = false
    case class TestState() extends State:
        override def handle(e : GameStateEvents) : State = 
            handled = true
            return TestState()
    val teststate = TestState()
    "A GameStateContext" should {
        "have a variable state in which the current state of the game is tracked" in {
            GameStateContext.state = StartState()
            GameStateContext.state.isInstanceOf[StartState] should be(true)
        }
        "have a variable maxplayers which tracks how many players are set to play" in {
            GameStateContext.maxplayers.equals(2) should be(true)
        }
        "have a function handle, which passes an event to the current state to handle, returning a new or changed state to keep track of" in {
            GameStateContext.state = teststate
            GameStateContext.handle(GameStateEvents.Start)
            GameStateContext.state.isInstanceOf[TestState] should be(true)
            handled should be(true)
        }
        "have a function to return the current state" in {
            val state = GameStateContext.getState()
            state.isInstanceOf[TestState] should be(true)
        }
        "have a function to set a state" in {
            GameStateContext.setState(StartState())
            GameStateContext.state.isInstanceOf[StartState] should be(true)
        }
    }
