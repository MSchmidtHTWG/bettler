package de.htwg.se.bettler
package controller

import model._
import util._

case class Controller(var game : Option[Game]) extends Observable with Caretaker:
    override def toString = 
        game match
            case Some(g) => g.toString
            case None => "Currently no game running."

    val undomanager = UndoManager()

    def doAndNotify(p : (Cards) => Option[Game], cards : Cards) : Unit =
        undomanager.doStep(PlayCommand(this))
        game = p(cards)
        notifyObservers

    def doAndNotify(p : (String) => Option[Game], kind : String) : Unit =
        val newGame = p(kind)
        newGame match
            case Some(newGame) => game = Some(newGame)
            case None => game = None
        notifyObservers

    def doAndNotify(p : () => Option[Game]) : Unit =
        undomanager.doStep(PlayCommand(this))
        val newGame = p()
        newGame match
            case Some(newGame) => game = Some(newGame)
            case None => println("")
        notifyObservers

    def restore(p : () => Memento) : Unit =
        if !stack.isEmpty then
            game match
                case Some(g) => game = g.restore(p())
                case None => game = None
            notifyObservers

    def play(cards : Cards) : Option[Game] =
        if !GameStateContext.state.isInstanceOf[PlayerTurnState] then
            return None
        game match
            case Some(newGame) => Some(newGame.play(cards))
            case None => None
            
    def skip() : Option[Game] =
        if GameStateContext.state.isInstanceOf[StartState] then
            return None
        game match
            case Some(newGame) => Some(newGame.skip())
            case None => None

    def newGame(kind : String) : Option[Game] =
        val newgame = Game(kind)
        newgame match
            case Some(someGame) => Some(someGame)
            case None => None

    def addMemento() : Unit = 
        game match
            case Some(g) => stack.push(g.save())
            case None => return

    def getMemento() : Memento =
        game match
            case Some(g) => stack.pop()

    def undo : Unit = 
        undomanager.undoStep
        notifyObservers

    def redo : Unit =
        undomanager.redoStep
        notifyObservers