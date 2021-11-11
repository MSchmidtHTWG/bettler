package de.htwg.se.bettler
package model

enum Value (value : Int):
    override def toString =
        value match {
            case 7 => value.toString
            case 8 => value.toString
            case 9 => value.toString
            case 10 => value.toString
            case 11 => "J"
            case 12 => "Q"
            case 13 => "K"
            case 14 => "A"
        }

    def getValue = value
    case Seven extends Value(7)
    case Eight extends Value(8)
    case Nine extends Value(9)
    case Ten extends Value(10)
    case Jack extends Value(11)
    case Queen extends Value(12)
    case King extends Value(13)
    case Ace extends Value(14)