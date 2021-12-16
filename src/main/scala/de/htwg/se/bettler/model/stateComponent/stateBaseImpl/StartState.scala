package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

case class StartState() extends State:
    def handle(e: Events) : State =
        e match 
            case Events.Start => PlayerTurnState(0,2)
            case _ => this