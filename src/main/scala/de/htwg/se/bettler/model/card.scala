package de.htwg.se.bettler
package model

case class Card(s : Symbol, v : Value) {
    override def toString = s.toString + v.toString
    def sameValue(c : Card) = this.v == c.v
    def isHigher(c : Card) = this.v.getValue > c.v.getValue
}