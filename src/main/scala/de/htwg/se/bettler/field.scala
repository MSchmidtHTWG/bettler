package scala
object field {



val eol = sys.props("line.separator")  

def printCard(card: Tuple2[Char,String]) =
  var s = card.toString()
  s = s.filter(!"()".contains(_))
  print("[")
  print(s) 
  print("]")  
  print("")


def printField(board: List[(Char, String)], player1: List[(Char, String)],player2: List[(Char, String)]) =
   
  print("----------------------------------------------------" + eol)
  print("Spieler 1 :"+ eol)
  player1.foreach{printCard}
  print(eol)
  print("----------------------------------------------------"+ eol)
  print("Spieler 2 :"  + eol)
  player2.foreach{printCard}
  print(eol)
  print("----------------------------------------------------"+ eol)
  board.foreach{printCard}
  print(eol)
  print("----------------------------------------------------"+ eol)

}