package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

case class StartState() extends State:
    def handle(event: GameStateEvents) : State =
        event match 
            case GameStateEvents.Start => PlayerTurnState(0,2)
            case _ => this