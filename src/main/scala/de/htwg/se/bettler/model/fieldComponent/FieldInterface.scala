package de.htwg.se.bettler
package model
package fieldComponent

import gameComponent._
import cardComponent._

trait FieldInterface:
    def printCard(card : CardInterface) : String
    def printField() : String
    def bar(cellwidth: Int) : String