package de.htwg.se.bettler
package controller

import model._
import util._

case class Controller(var game : Game) extends Observable with Caretaker:
    override def toString = game.toString
    val undomanager = UndoManager()
    def doAndNotify(p : (Cards) => Game, cards : Cards) : Unit =
        undomanager.doStep(PlayCommand(this))
        game = p(cards)
        notifyObservers
    def doAndNotify(p : (String) => Game, kind : String) : Unit =
        game = p(kind)
        notifyObservers
    def doAndNotify(p : () => Game) : Unit =
        undomanager.doStep(PlayCommand(this))
        game = p()
        notifyObservers
    def restore(p : () => Memento) : Unit =
        if !stack.isEmpty then
            game = game.restore(p())
            notifyObservers
    def play(cards : Cards) : Game =
        game.play(cards)
    def skip() : Game =
        game.skip()
    def newGame(kind : String) : Game =
        Game(kind)
    def addMemento() : Unit = stack.push(game.save())
    def getMemento() : Memento = stack.pop()
    def undo : Unit = 
        undomanager.undoStep
        notifyObservers
    def redo : Unit =
        undomanager.redoStep
        notifyObservers