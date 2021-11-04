package scala
class field {

//val i = 0
val cardNumber = 7
val cellSize = 4
val playerNumber = 2
val fieldSize = 2
val eol = sys.props("line.separator")  

def printCard(card: Tuple2[Char,String]) =
  var s = card.toString()
  s = s.filter(!"()".contains(_))
  print("[")
  print(s) 
  print("]")  
  print("")


def printFieldEmpty(playerNumber: Int = 2, fieldsize: Int = 2, cellSize: Int = 4) =
  println("Welcome to Bettler")
  for (i <- 1 to playerNumber){
    println(player(playerNumber = i))
  }
  println("Spielfeld :")
  println(mesh(cellSize, fieldSize, fieldSize))

def printField(Playernumber: Int = 2, Player1: List[(Char, String)],Player2: List[(Char, String)]) =
  

  println("__________________________________")
  println("")
  println("Spieler 1 :")
  Player1.foreach{printCard}
  
  println("")
  println("__________________________________")
  println("Spieler 2 :")
  
  Player2.foreach{printCard}
 

def player(playerNumber: Int = 1, cardNumber: Int = 7) =
  "Spieler " + playerNumber + " :" + eol + mesh(cellNum = cardNumber)

def cell(cellwidth: Int = 3, cellNum: Int =3) = 
  ("|" + " " * cellwidth) * cellNum + "|" + eol

def bar(cellwidth: Int = 4, cellNum: Int =7) =
  ("+" + "-" * cellwidth) * cellNum + "+" + eol


def mesh(cellwidth: Int = 4, cellNum: Int = 1, rows: Int = 1) =
  (bar(cellwidth, cellNum) + cell(cellwidth,cellNum)) * rows + bar(cellwidth, cellNum)
}
