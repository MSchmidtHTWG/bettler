package de.htwg.se.bettler
package model

object DeckStrategy:
    def strategy1() = System.currentTimeMillis()
    def strategy2() = 0
    def execute(strategy:() => Long) = strategy()