@main def Main: Unit =
  
  println("Welcome to Bettler")
  println(mesh())

val eol = sys.props("line.separator")  

def cell(cellwidth: Int = 3, cellNum: Int =3) = 
  ("|" + " " * cellwidth) * cellNum + "|" + eol

def bar(cellwidth: Int = 3, cellNum: Int =3) =
  ("+" + "-" * cellwidth) * cellNum + "+" + eol


def mesh(cellwidth: Int = 3, cellNum: Int = 3) =
  (bar(cellwidth, cellNum) + cell(cellwidth,cellNum)) * cellNum + bar(cellwidth, cellNum)





 

  

