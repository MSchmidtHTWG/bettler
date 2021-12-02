package de.htwg.se.bettler
package model

enum Value (value : String):
    override def toString = value

    def getValue =
        value match {
            case "7" => 7
            case "8" => 8
            case "9" => 9
            case "10" => 10
            case "J" => 11
            case "Q" => 12
            case "K" => 13
            case "A" => 14
            case _ => 0
        }
    case Seven extends Value("7")
    case Eight extends Value("8")
    case Nine extends Value("9")
    case Ten extends Value("10")
    case Jack extends Value("J")
    case Queen extends Value("Q")
    case King extends Value("K")
    case Ace extends Value("A")
    case Empty extends Value("")