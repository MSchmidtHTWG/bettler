package de.htwg.se.bettler
package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.bettler.model.stateComponent.GameStateEvents

class GameStateEventsSpec extends AnyWordSpec with Matchers:
    "Enum GameStateEvents" should {
        "have a subclass start to signal that a game is about to start" in {
            GameStateEvents.Start.toString.equals("start") should be(true)
        }
        "have a subclass skip to signal that a player wants to skip his turn" in {
            GameStateEvents.Skip.toString.equals("skip") should be(true)
        }
        "have a subclass finished to signal that a game has finished" in {
            GameStateEvents.Finished.toString.equals("finished") should be(true)
        }
    }