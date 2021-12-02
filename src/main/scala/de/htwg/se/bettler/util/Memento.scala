package de.htwg.se.bettler
package util

import de.htwg.se.bettler.model._

trait Memento:
    def state() : State
    def game() : Option[Game]

case class GameMemento(savegame : Option[Game], savestate : State) extends Memento:
    def state() : State = savestate
    def game() : Option[Game] = savegame