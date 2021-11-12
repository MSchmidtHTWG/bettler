package de.htwg.se.bettler
package model
object Field {



val eol = sys.props("line.separator")  

def printCard(card : Card) : String =
  var s = card.toString()
  s = s.filter(!"()".contains(_))
  var r = "[" + s + "]"
  return r


def printField(board: Set[Card], player1: Set[Card],player2: Set[Card]) : String =
  
  var r = bar() + "Spieler 1" + eol
  player1.foreach{r += printCard(_)}
  r += eol + bar() + "Spieler 2" + eol
  player2.foreach{r += printCard(_)}
  r += eol + bar()
  board.foreach{r+= printCard(_)}
  r += eol + bar()
  return r


def bar(cellwidth: Int = 50) =
  "-" * cellwidth + eol
}