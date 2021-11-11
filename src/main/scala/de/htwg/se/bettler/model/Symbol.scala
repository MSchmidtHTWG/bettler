package scala
package de.htwg.se.bettler.model

enum Symbol(symbol : String):
    override def toString = symbol
    case Hearts extends Symbol("H")
    case Diamonds extends Symbol("D")
    case Clubs extends Symbol("C")
    case Spades extends Symbol("S")