package de.htwg.se.bettler
package model
package cardComponent
package cardBaseImpl

import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._

import java.awt.Image
import java.io.File
import java.nio.file.Paths
import javax.imageio.ImageIO
import scala.annotation.meta.setter
import scala.util.Failure
import scala.util.Success
import scala.util.Try
import cardComponent.Symbol
    
case class Card(symbol : Symbol, value : Value) extends CardInterface:
    override def toString = symbol.toString + value.toString
    def image = new File(f"src/main/scala/de/htwg/se/bettler/model/cardcomponent/cardpictures/" + symbol.toString + value.toString + ".png")
    def sameValue(card : CardInterface) = this.value == card.getValue
    def isHigher(card : CardInterface) = this.value.getValue > card.getValue.getValue
    def toCards = Cards(Set(this))
    def getSymbol = symbol
    def getValue = value
    def intValue = value.getValue

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

case class NoCardException(message: String) extends Exception(message) 