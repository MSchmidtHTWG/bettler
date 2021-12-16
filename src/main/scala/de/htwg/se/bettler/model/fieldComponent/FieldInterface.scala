package de.htwg.se.bettler
package model
package fieldBaseImpl

import gameComponent._
import cardComponent.cardBaseImpl.Cards
import cardComponent.cardBaseImpl.Card

trait FieldInterface:
    def printCard(card : Card) : String
    def printField() : String
    def bar(cellwidth: Int) : String