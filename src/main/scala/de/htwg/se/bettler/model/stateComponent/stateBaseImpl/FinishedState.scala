package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

case class FinishedState(winner : Int, loser : Int) extends State:
    def handle(event: GameStateEvents) : State =
        event match
            case GameStateEvents.Start => PlayerTurnState(loser, 2)
            case _ => this