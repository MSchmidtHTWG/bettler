package de.htwg.se.bettler
package model

trait State:
    def handle(e: Event) : State

object GameStateContext:
    var state : State = StartState()
    def handle(e: Event) = state = state.handle(e)
    def getState() = state
    def setState(s : State) = state = s