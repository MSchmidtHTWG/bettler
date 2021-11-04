package scala
class field {



val eol = sys.props("line.separator")  

def printCard(card: Tuple2[Char,String]) =
  var s = card.toString()
  s = s.filter(!"()".contains(_))
  print("[")
  print(s) 
  print("]")  
  print("")


def printField(Playernumber: Int = 2, Player1: List[(Char, String)],Player2: List[(Char, String)]) =
  

  
  println("----------------------------------------------------")
  println("Spieler 1 :")
  Player1.foreach{printCard}
  println("")
  println("----------------------------------------------------")
  println("Spieler 2 :")
  Player2.foreach{printCard}
  println("")
  println("----------------------------------------------------")


}