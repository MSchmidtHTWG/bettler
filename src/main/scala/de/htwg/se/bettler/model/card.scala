package de.htwg.se.bettler
package model

import model.Symbol
import scala.annotation.meta.setter
import scala.util.Try
import scala.util.Failure
import scala.util.Success

case class Card(symbol : Symbol, value : Value):
    override def toString = symbol.toString + value.toString
    def sameValue(card : Card) = this.value == card.value
    def isHigher(card : Card) = this.value.getValue > card.value.getValue

object Card :
    def apply(input : String) : Try[Card] =
        if input.length < 2 || input.length > 4 then return Failure(NoCardException("The string is too short or too long to be a card."))
        val s = input(0)
        val v = input.slice(1, input.length)
        val sym = s match
            case 'H' => Symbol.Hearts
            case 'D' => Symbol.Diamonds
            case 'S' => Symbol.Spades
            case 'C' => Symbol.Clubs
            case _ => Symbol.Empty
        val va = v match
            case "7" => Value.Seven
            case "8" => Value.Eight
            case "9" => Value.Nine
            case "10" => Value.Ten
            case "J" => Value.Jack
            case "Q" => Value.Queen
            case "K" => Value.King
            case "A" => Value.Ace
            case _ => Value.Empty
        if sym == Symbol.Empty || va == Value.Empty then Failure(NoCardException("The string is not a card."))
        else Success(Card(sym, va))

//class NoCardException(message : String) extends Exception(message):
    //def this() = this("The string is not a card.")
case class NoCardException(message: String) extends Exception(message) 