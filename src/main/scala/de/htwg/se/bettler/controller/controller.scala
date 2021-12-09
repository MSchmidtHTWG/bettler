package de.htwg.se.bettler
package controller

import model._
import util._
import scala.swing.Publisher
import scala.swing.event.Event

case class Controller(var game : Option[Game]) extends Publisher with Observable with Caretaker:
    override def toString = 
        game match
            case Some(g) => g.toString
            case None => "Currently no game running."

    val undomanager = UndoManager()

    def doAndNotify(p : (Cards) => Option[Game], cards : Cards) : Unit =
        val newGame = p(cards)
        newGame match
            case Some(newGame) => 
                undomanager.doStep(PlayCommand(this))
                game = Some(newGame)
            case None => game = newGame
        notifyObservers

    def doAndNotify(p : (String) => Option[Game], kind : String) : Unit =
        val newGame = p(kind)
        newGame match
            case Some(newGame) => game = Some(newGame)
            case None => game = None
        notifyObservers

    def doAndNotify(p : () => Option[Game]) : Unit =
        val newGame = p()
        newGame match
            case Some(newGame) => 
                undomanager.doStep(PlayCommand(this))
                game = Some(newGame)
            case None => game = newGame
        notifyObservers

    def restore : Unit =
        if !stack.isEmpty then
            game match
                case Some(g) => game = Some(g.restore(this.getMemento()))
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
            case Some(someGame) => 
                publish(new GameChanged())
                Some(someGame)
            case None => None

    def addMemento() : Unit = 
        game match
            case Some(g) => stack.push(g.save())
            case None => return

    def getMemento() : Memento =
        stack.pop()

    def undo : Unit = 
        undomanager.undoStep
        notifyObservers

    def redo : Unit =
        undomanager.redoStep
        notifyObservers