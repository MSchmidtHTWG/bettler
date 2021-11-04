package scala
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class fieldSpec extends AnyWordSpec {

    "(H,7 when Printed)" should {
      var card1 = ('H', "7");
      "be [H,7]" in {
        field.printCard(card1)

    }
}
    "(A playing field when Printed)" should {
       var player1 = ('H', "7")::('S', "ACE") :: Nil;
       var player2 = ('H', "9")::('C', "J") :: Nil;
       var board1 = ('D', "10")::('C', "7") :: Nil;
      "be [H,7]" in {
        field.printField(board1,player1,player2)

    }
}
}