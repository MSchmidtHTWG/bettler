package de.htwg.se.bettler
package model

case class FinishedState() extends State:
    def handle(e: Events) : State =
        e match
            case Events.Start => PlayerTurnState(0, 2)
            case _ => this