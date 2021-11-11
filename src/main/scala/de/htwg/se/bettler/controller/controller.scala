package de.htwg.se.bettler
package controller

import model.Game
import util.Observable

case class Controller(var game : Game) extends Observable:
    override def toString = game.toString
    def handle(input : String): Unit =
        game.handle(input)
        notifyObservers