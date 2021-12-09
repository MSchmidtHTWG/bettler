package de.htwg.se.bettler
package model

trait State:
    def handle(e: Events) : State

object GameStateContext:
    var state : State = StartState()
    def handle(e: Events) = state = state.handle(e)
    def getState() = state
    def setState(s : State) = state = s