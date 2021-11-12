package de.htwg.se.bettler
package model

import model.Symbol
import scala.annotation.meta.setter

case class Card(symbol : Symbol, value : Value) {
    override def toString = symbol.toString + value.toString
    def sameValue(card : Card) = this.value == card.value
    def isHigher(card : Card) = this.value.getValue > card.value.getValue
    def returnCard(input : String) : Option[Card] = {
        if input.length < 2 then return None
        val s = input(0)
        val v = input.slice(1, input.length)
        val sym = s match {
            case 'H' => Symbol.Hearts
            case 'D' => Symbol.Diamonds
            case 'S' => Symbol.Spades
            case 'C' => Symbol.Clubs
            case _ => Symbol.Empty
        }
        val va = v match {
            case "7" => Value.Seven
            case "8" => Value.Eight
            case "9" => Value.Nine
            case "10" => Value.Ten
            case "J" => Value.Jack
            case "Q" => Value.Queen
            case "K" => Value.King
            case "A" => Value.Ace
            case _ => Value.Empty
        } 
        if (sym == Symbol.Empty || va == Value.Empty) {
            return None
        } else {
            return Some(Card(sym, va))
        }
    }
}

object Card :
    def returnCard(input : String) : Option[Card] = {
        if input.length < 2 then return None
        val s = input(0)
        val v = input.slice(1, input.length)
        val sym = s match {
            case 'H' => Symbol.Hearts
            case 'D' => Symbol.Diamonds
            case 'S' => Symbol.Spades
            case 'C' => Symbol.Clubs
            case _ => Symbol.Empty
        }
        val va = v match {
            case "7" => Value.Seven
            case "8" => Value.Eight
            case "9" => Value.Nine
            case "10" => Value.Ten
            case "J" => Value.Jack
            case "Q" => Value.Queen
            case "K" => Value.King
            case "A" => Value.Ace
            case _ => Value.Empty
        } 
        if (sym == Symbol.Empty || va == Value.Empty) {
            return None
        } else {
            return Some(Card(sym, va))
        }
    }