package de.htwg.se.bettler
package model
package stateComponent

trait State:
    def handle(e: Events) : State
