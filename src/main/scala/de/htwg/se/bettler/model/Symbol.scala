package de.htwg.se.bettler
package model

enum Symbol (symbol : String):
    override def toString = symbol
    case Hearts extends Symbol("H")
    case Diamonds extends Symbol("D")
    case Clubs extends Symbol("C")
    case Spades extends Symbol("S")
    case Empty extends Symbol("")

object Symbol:
    def apply() = Symbol