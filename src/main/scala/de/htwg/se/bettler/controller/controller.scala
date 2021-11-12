package de.htwg.se.bettler
package controller

import model.Game
import util.Observable
import model.Card

case class Controller(var game : Game) extends Observable:
    override def toString = game.toString
    def play(cards : Set[Card]) : Unit =
        game = game.play(cards)
        notifyObservers
    def start() : Unit = 
        game = game.start()
        notifyObservers
    def skip() : Unit =
        game = game.skip()
        notifyObservers