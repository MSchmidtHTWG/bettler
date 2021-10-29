import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class Test1 extends AnyWordSpec {


 "Main" should{
   "have a bar as String of Form '+----+----+----+----+----+----+----+'" in {
    bar() should be ("+----+----+----+----+----+----+----+" + eol)
   }
   "have a scalable bar" in {
    bar(1,1) should be ("+-+" + eol)
    bar(2,1) should be ("+--+" + eol)
    bar(2,2) should be ("+--+--+" + eol)
   }
   "have a cell as String of Form '|   |   |   |" in {
    cell() should be ("|   |   |   |" + eol)
   }
   "have a scalable cell" in {
    cell(1,1) should be ("| |" + eol)
    cell(2,1) should be ("|  |" + eol)
    cell(2,2) should be ("|  |  |" + eol)
   }
   "have a player as String of Form 'Spieler 1 : + eol + +----+----+----+----+----+----+----+ + eol + |    |    |    |    |    |    |    | + eol + +----+----+----+----+----+----+----+" in {
     player() should be ("Spieler 1 :" + eol + "+----+----+----+----+----+----+----+" + eol + "|    |    |    |    |    |    |    |" + eol + "+----+----+----+----+----+----+----+" + eol)
   }
   "have a number of players with varying cards" in {
     player(1,1) should be ("Spieler 1 :" + eol + "+----+" + eol + "|    |" + eol + "+----+" + eol)
     player(2,2) should be ("Spieler 2 :" + eol + "+----+----+" + eol + "|    |    |" + eol + "+----+----+" + eol)
   }
   "have a mesh as String of Form '+----+ + eol + |    | + eol + +----+ + eol" in {
     mesh() should be ("+----+" + eol + "|    |" + eol + "+----+" + eol)
   }
   "have a scalable mesh" in {
     mesh(1,1,1) should be ("+-+" + eol + "| |" + eol + "+-+" + eol)
     mesh(3,2,1) should be ("+---+---+" + eol + "|   |   |" + eol + "+---+---+" + eol)
     mesh(1,1,2) should be ("+-+" + eol + "| |" + eol + "+-+" + eol +"| |" + eol + "+-+" + eol)
   }
 } 
}