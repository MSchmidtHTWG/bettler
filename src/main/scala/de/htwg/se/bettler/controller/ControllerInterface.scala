package de.htwg.se.bettler
package controller

import model.GameChanged
import model.gameComponent.Game
import model.cardComponent._
import util._
import scala.swing.Publisher
import scala.swing.event.Event

trait ControllerInterface extends Publisher with Observable with Caretaker:
    def doAndNotify(p : (CardsInterface) => Option[Game], cards : CardsInterface) : Unit
    def doAndNotify(p : (String) => Option[Game], kind : String) : Unit
    def doAndNotify(p : () => Option[Game]) : Unit
    def nextRound() : Option[Game]
    def restore : Unit
    def play(cards : CardsInterface) : Option[Game]
    def skip() : Option[Game]
    def newGame(kind : String) : Option[Game]
    def undo : Unit
    def redo : Unit
    def returnGame : Option[Game]
    def setBeautifulField : Unit
    def exit : Unit