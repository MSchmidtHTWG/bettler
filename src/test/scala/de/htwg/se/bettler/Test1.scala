package scala
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class Test1 extends AnyWordSpec {

 val field = new field
 "field" should{
   "have a bar as String of Form '+----+----+----+----+----+----+----+'" in {
    field.bar() should be ("+----+----+----+----+----+----+----+" + field.eol)
   }
   "have a scalable bar" in {
    field.bar(1,1) should be ("+-+" + field.eol)
    field.bar(2,1) should be ("+--+" + field.eol)
    field.bar(2,2) should be ("+--+--+" + field.eol)
   }
   "have a cell as String of Form '|   |   |   |" in {
    field.cell() should be ("|   |   |   |" + field.eol)
   }
   "have a scalable cell" in {
    field.cell(1,1) should be ("| |" + field.eol)
    field.cell(2,1) should be ("|  |" + field.eol)
    field.cell(2,2) should be ("|  |  |" + field.eol)
   }
   "have a player as String of Form 'Spieler 1 : + eol + +----+----+----+----+----+----+----+ + eol + |    |    |    |    |    |    |    | + eol + +----+----+----+----+----+----+----+" in {
     field.player() should be ("Spieler 1 :" + field.eol + "+----+----+----+----+----+----+----+" + field.eol + "|    |    |    |    |    |    |    |" + field.eol + "+----+----+----+----+----+----+----+" + field.eol)
   }
   "have a number of players with varying cards" in {
     field.player(1,1) should be ("Spieler 1 :" + field.eol + "+----+" + field.eol + "|    |" + field.eol + "+----+" + field.eol)
     field.player(2,2) should be ("Spieler 2 :" + field.eol + "+----+----+" + field.eol + "|    |    |" + field.eol + "+----+----+" + field.eol)
   }
   "have a mesh as String of Form '+----+ + eol + |    | + eol + +----+ + eol" in {
     field.mesh() should be ("+----+" + field.eol + "|    |" + field.eol + "+----+" + field.eol)
   }
   "have a scalable mesh" in {
     field.mesh(1,1,1) should be ("+-+" + field.eol + "| |" + field.eol + "+-+" + field.eol)
     field.mesh(3,2,1) should be ("+---+---+" + field.eol + "|   |   |" + field.eol + "+---+---+" + field.eol)
     field.mesh(1,1,2) should be ("+-+" + field.eol + "| |" + field.eol + "+-+" + field.eol +"| |" + field.eol + "+-+" + field.eol)
   }
 } 
}