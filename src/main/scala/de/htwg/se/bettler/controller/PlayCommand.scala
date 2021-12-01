package de.htwg.se.bettler
package controller

import util._
import model._

class PlayCommand(controller : Controller) extends Command:
    var memento = GameMemento(controller.game, GameStateContext.getState())
    override def doStep : Unit =
        memento = GameMemento(controller.game, GameStateContext.getState())
    override def undoStep : Unit =
        val newMemento = GameMemento(controller.game, GameStateContext.getState())
        controller.game = memento.savegame
        GameStateContext.setState(memento.savestate)
        memento = newMemento
    override def redoStep : Unit =
        val newMemento = GameMemento(controller.game, GameStateContext.getState())
        controller.game = memento.savegame
        GameStateContext.setState(memento.savestate)
        memento = newMemento

        