package scala
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalactic.Prettifier.default
class fieldSpec extends AnyWordSpec {

       var player1 = ('H', "7")::('S', "ACE") :: Nil;
       var player2 = ('H', "9")::('C', "J") :: Nil;
       var board1 = ('D', "10")::('C', "7") :: Nil;
       var card = ('H', "7")

    "field " should{

   "have a bar as String of Form '--------------------------------------------------'" in {
    field.bar() should be ("--------------------------------------------------" + field.eol)
   }
   "have a scalable bar" in {
    field.bar(100) should be ("----------------------------------------------------------------------------------------------------" + field.eol)
    field.bar(1) should be ("-" + field.eol)
    field.bar(15) should be ("---------------" + field.eol)
   }
   "have printeble cards '[H,7]]'" in {
    field.printCard(card) should be ("[H,7]")
   }
   "have a printable Board" in {
     field.printField(board1,player1,player2) should be(field.bar() + "Spieler 1" + field.eol + "[H,7][S,ACE]" +field.eol + field.bar()+"Spieler 2" + field.eol + "[H,9][C,J]" + field.eol+bar()+ "[D,10][C,7]"+field.eol + field.bar())
   }


    }
  }







