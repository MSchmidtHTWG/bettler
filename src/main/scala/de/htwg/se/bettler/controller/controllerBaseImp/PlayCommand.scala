package de.htwg.se.bettler
package controller
package controllerBaseImp

import controller._
import util._
import model._

class PlayCommand(controller : Controller) extends Command:
    assert(controller.game.isDefined)
    var memento = GameMemento(controller.game.get, GameStateContext.getState())

    override def doStep : Unit =
        memento = GameMemento(controller.game.get, GameStateContext.getState())   

    override def undoStep : Unit =
        val newMemento = GameMemento(controller.game.get, GameStateContext.getState())
        controller.game = Some(memento.savegame)
        GameStateContext.setState(memento.savestate)
        memento = newMemento

    override def redoStep : Unit =
        val newMemento = GameMemento(controller.game.get, GameStateContext.getState())
        controller.game = Some(memento.savegame)
        GameStateContext.setState(memento.savestate)
        memento = newMemento