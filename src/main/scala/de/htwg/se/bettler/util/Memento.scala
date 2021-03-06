package de.htwg.se.bettler
package util

import de.htwg.se.bettler.model.gameComponent.Game
import de.htwg.se.bettler.model.stateComponent._

trait Memento:
    def state() : State
    def game() : Game

case class GameMemento(savegame : Game, savestate : State) extends Memento:
    def state() : State = savestate
    def game() : Game = savegame