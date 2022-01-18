package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerTurnStateSpec extends AnyWordSpec with Matchers:
    "A playerturnstate" should {
        "have a variable to track whos turn it is" in {
            PlayerTurnState(0,2).currentPlayer should be(0)
        }
        "have a variable to track how many players are playing" in {
            PlayerTurnState(0,2).maxPlayers should be(2)
        }
        "return a playerturnstate with the next player set to have his turn when handleing a skip event" in {
            PlayerTurnState(0,2).handle(GameStateEvents.Skip).asInstanceOf[PlayerTurnState].currentPlayer should be(1)
        }
        "return a playerturnstate with the first player to have his turn when handleing a start event" in {
            PlayerTurnState(1,2).handle(GameStateEvents.Start).asInstanceOf[PlayerTurnState].currentPlayer should be(0)
        }
        "return a finishedstate with the currentplayer declared as winner and the other as loser" in {
            PlayerTurnState(0,2).handle(GameStateEvents.Finished).asInstanceOf[FinishedState].winner should be(0)
            PlayerTurnState(0,2).handle(GameStateEvents.Finished).asInstanceOf[FinishedState].loser should be(1)
        }
    }