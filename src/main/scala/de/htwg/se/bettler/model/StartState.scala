package de.htwg.se.bettler
package model

case class StartState() extends State:
    def handle(e: Event) : State =
        e match 
            case Event.Start => PlayerTurnState(0,2)
            case _ => this