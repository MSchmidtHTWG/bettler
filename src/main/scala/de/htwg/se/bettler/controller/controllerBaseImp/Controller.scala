package de.htwg.se.bettler
package controller
package controllerBaseImp

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import de.htwg.se.bettler.BettlerModule
import model.gameComponent.Game
import model.cardComponent._
import util._
import scala.swing.Publisher
import scala.swing.event.Event
import model._

case class Controller @Inject() (var game : Option[Game]) extends ControllerInterface:

    override def toString = 
        game match
            case Some(g) => g.toString
            case None => "Currently no game running."

    def returnGame = game
    val undomanager = util.UndoManager()

    def doAndNotify(p : (CardsInterface) => Option[Game], cards : CardsInterface) : Unit =
        undomanager.doStep(PlayCommand(this))
        val newGame = p(cards)
        newGame match
            case Some(newGame) => 
                game = Some(newGame)
            case None => 
                game = newGame
        notifyObservers
        publish(new GameChanged())

    def doAndNotify(p : (String) => Option[Game], kind : String) : Unit =
        val newGame = p(kind)
        newGame match
            case Some(newGame) => game = Some(newGame)
            case None => game = None
        notifyObservers
        publish(new GameChanged())

    def doAndNotify(p : () => Option[Game]) : Unit =
        undomanager.doStep(PlayCommand(this))
        val newGame = p()
        newGame match
            case Some(newGame) => 
                game = Some(newGame)
            case None => 
                game = newGame
        notifyObservers
        publish(new GameChanged())

    def restore : Unit =
        if !stack.isEmpty then
            game match
                case Some(g) => game = Some(g.restore(this.getMemento()))
                case None => game = None
            notifyObservers
            publish(new GameChanged())

    def play(cards : CardsInterface) : Option[Game] =
        game match
            case Some(newGame) => Some(newGame.play(cards))
            case None => None
            
    def skip() : Option[Game] =
        game match
            case Some(newGame) => Some(newGame.skip())
            case None => None

    def newGame(kind : String) : Option[Game] =
        val newgame = Game(kind)
        kind match
            case "pve" => Some(newgame)
            case "pvp" => Some(newgame)
            case _ => None

    def nextRound() : Option[Game] =
        game match
            case Some(newGame) => Some(newGame.nextRound)
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
        publish(new GameChanged())

    def redo : Unit =
        undomanager.redoStep
        notifyObservers
        publish(new GameChanged())

    def exit : Unit =
        publish(new CloseEvent)