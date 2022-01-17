package de.htwg.se.bettler
package model
package stateComponent

import stateBaseImpl._

object GameStateContext:
    var state : State = StartState()
    var maxplayers = 2
    def handle(event: GameStateEvents) = state = state.handle(event)
    def getState() = state
    def setState(s : State) = state = s