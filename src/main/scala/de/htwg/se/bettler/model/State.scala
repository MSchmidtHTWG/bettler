package de.htwg.se.bettler
package model

trait State:
    def play(cards : Set[Card], game : Game) : Game

