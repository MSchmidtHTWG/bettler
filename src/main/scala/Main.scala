@main def Main: Unit =
  


  println("Welcome to Bettler")
  //println(mesh())

  for (i <- 1 to playerNumber){
    println("Spieler "+ i + " :")
    println(mesh(cellSize,cardNumber))
  }
  println("Spielfeld :")
  println(mesh(cellSize, fieldSize, fieldSize))


  
//val i = 0
val cardNumber = 7
val cellSize = 4
val playerNumber = 2
val fieldSize = 2
val eol = sys.props("line.separator")  

def cell(cellwidth: Int = 3, cellNum: Int =3) = 
  ("|" + " " * cellwidth) * cellNum + "|" + eol

def bar(cellwidth: Int = 4, cellNum: Int =7) =
  ("+" + "-" * cellwidth) * cellNum + "+" + eol


def mesh(cellwidth: Int = 4, cellNum: Int = 7, rows: Int = 1) =
  (bar(cellwidth, cellNum) + cell(cellwidth,cellNum)) * rows + bar(cellwidth, cellNum)





 

  

