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
  

  
  println("----------------------------------------------------")
  println("Spieler 1 :")
  player1.foreach{printCard}
  println("")
  println("----------------------------------------------------")
  println("Spieler 2 :")
  player2.foreach{printCard}
  println("")
  println("----------------------------------------------------")
  board.foreach{printCard}
  println("----------------------------------------------------")



}