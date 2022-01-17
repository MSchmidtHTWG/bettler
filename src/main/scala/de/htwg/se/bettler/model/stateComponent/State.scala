package de.htwg.se.bettler
package model
package stateComponent

import stateComponent.GameStateContext

trait State:
    def handle(event: GameStateEvents) : State
