package de.htwg.se.bettler.model

enum Value (value : Int):
    override def toString = value.toString;
    def getValue = value
    case Seven extends Value(7)
    case Eight extends Value(8)
    case Nine extends Value(9)
    case Ten extends Value(10)
    case Jack extends Value(11)
    case Queen extends Value(12)
    case King extends Value(13)
    case Ace extends Value(14)