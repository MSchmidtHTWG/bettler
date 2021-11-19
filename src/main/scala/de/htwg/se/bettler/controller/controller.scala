package de.htwg.se.bettler
package controller

import model.Game
import util.Observable
import model.Card

case class Controller(var game : Game) extends Observable:
    override def toString = game.toString
    def doAndNotify(p : (Set[Card]) => Game, s : Set[Card]) : Unit =
        p(s)
        notifyObservers;
    def play(cards : Set[Card]) : Game =
        game.play(cards)
    def start() : Unit = 
        game = Game()
        notifyObservers
    def skip() : Unit =
        game = game.skip()
        notifyObservers