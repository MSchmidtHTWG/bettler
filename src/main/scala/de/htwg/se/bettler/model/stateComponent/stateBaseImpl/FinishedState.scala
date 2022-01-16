package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

case class FinishedState(winner : Int, loser : Int) extends State:
    def handle(e: Events) : State =
        e match
            case Events.Start => PlayerTurnState(loser, 2)
            case _ => this