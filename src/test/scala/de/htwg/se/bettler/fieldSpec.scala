package scala
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class fieldSpec extends AnyWordSpec {

       var player1 = ('H', "7")::('S', "ACE") :: Nil;
       var player2 = ('H', "9")::('C', "J") :: Nil;
       var board1 = ('D', "10")::('C', "7") :: Nil;
    "(H,7 when Printed)" should {
      var card1 = ('H', "7");
      "be [H,7]" in {
        println("")
        field.printCard(card1)

    }
}

        
    "have playying Field in Form of a playing field" in {
    field.printField(board1,player1,player2) should be ("----------------------------------------------------" +field.eol  + "Spieler 1 :"+field.eol +"[H,7][S,ACE]"+field.eol+"----------------------------------------------------"+field.eol+"Spieler 2 :"+field.eol+"[H,9][C,J]"+field.eol+"----------------------------------------------------"+field.eol+"[D,10][C,7]"+field.eol+"----------------------------------------------------"+field.eol)
   }
}

