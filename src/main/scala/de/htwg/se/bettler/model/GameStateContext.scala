package de.htwg.se.bettler
package model
import stateComponent._
import stateComponent.stateBaseImpl._

object GameStateContext:
    var state : State = StartState()
    var maxplayers = 2
    def handle(e: GameStateEvents) = state = state.handle(e)
    def getState() = state
    def setState(s : State) = state = s