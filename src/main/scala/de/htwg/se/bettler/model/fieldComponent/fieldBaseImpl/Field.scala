package de.htwg.se.bettler
package model
package fieldComponent
package fieldBaseImpl

import gameComponent._
import cardComponent.cardBaseImpl.Cards
import cardComponent.cardBaseImpl.Card

case class Field(game : Game) {

  val eol = sys.props("line.separator")  

  def printCard(card : Card) : String =
    var s = card.toString()
    s = s.filter(!"()".contains(_))
    var r = "[" + s + "]"
    return r


  def printField() : String =
    var r = "" // val StringBuilder?
    var i = 0
    for (players <- game.getPlayers())
      i += 1
      r += eol + bar() + "Player " + i + eol
      players.returnSet.foreach{r += printCard(_)}
    r += eol + bar()
    game.getBoard().returnSet.foreach{r+= printCard(_)}
    r += eol + bar()
    return r


  def bar(cellwidth: Int = 50) =
    "-" * cellwidth + eol
}