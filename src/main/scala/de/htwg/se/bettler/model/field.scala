package de.htwg.se.bettler
package model

class Field(game : Game) {

  val eol = sys.props("line.separator")  

  def printCard(card : Card) : String =
    var s = card.toString()
    s = s.filter(!"()".contains(_))
    var r = "[" + s + "]"
    return r


  def printField() : String =
    var r = bar() + "Spieler 1" + eol
    game.spieler1.returnSet.foreach{r += printCard(_)}
    r += eol + bar() + "Spieler 2" + eol
    game.spieler2.returnSet.foreach{r += printCard(_)}
    r += eol + bar()
    game.spielfeld.returnSet.foreach{r+= printCard(_)}
    r += eol + bar()
    return r


  def bar(cellwidth: Int = 50) =
    "-" * cellwidth + eol
}