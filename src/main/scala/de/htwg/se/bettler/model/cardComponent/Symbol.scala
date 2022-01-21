package de.htwg.se.bettler
package model
package cardComponent

enum Symbol (symbol : String):
    override def toString = symbol
    case Hearts extends Symbol("H")
    case Diamonds extends Symbol("D")
    case Clubs extends Symbol("C")
    case Spades extends Symbol("S")
    case Empty extends Symbol("")

object Symbol:
    def apply(symbol : String) =
        symbol match
            case "H" => Hearts
            case "D" => Diamonds
            case "C" => Clubs
            case "S" => Spades
            case _ => Empty
