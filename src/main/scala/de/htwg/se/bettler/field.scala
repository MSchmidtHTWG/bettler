package scala
object field {



val eol = sys.props("line.separator")  

def printCard(card: Tuple2[Char,String]) : String =
  var s = card.toString()
  s = s.filter(!"()".contains(_))
  var r = "[" + s + "]"
  return r


def printField(board: List[(Char, String)], player1: List[(Char, String)],player2: List[(Char, String)]) : String =
  
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