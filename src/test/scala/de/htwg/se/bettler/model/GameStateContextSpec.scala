package de.htwg.se.bettler
package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameStateContextSpec extends AnyWordSpec with Matchers {
    "A GameStateContext" when {
        "getting a state to be set" should {
            "save a state internally" in {
                val state = StartState()
                GameStateContext.setState(state)
                GameStateContext.state shouldBe(state)
            }
        }
    }
}
