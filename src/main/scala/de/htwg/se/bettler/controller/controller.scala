package de.htwg.se.bettler
package controller

import model.Game
import util.Observable
import model.Card
import model.Cards

case class Controller(var game : Game) extends Observable:
    override def toString = game.toString
    def doAndNotify(p : (Set[Card]) => Game, cards : Set[Card]) : Unit =
        game = p(cards)
        notifyObservers
    def doAndNotify(p : () => Game) : Unit =
        game = p()
        notifyObservers
    def play(cards : Set[Card]) : Game =
        game.play(Cards(cards))
    def start() : Game = 
        game.start()
    def skip() : Game =
        game.skip()