package scala
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class cardSpec extends AnyWordSpec{

  "Card" should {
  
    "returnValueInt " should{
     "return a value " in {
    card.returnValueInt("Ace") should be (7)
   }
}
    "should have a card return value in" {
     "return 0 " in {
    card.returnValueInt("7") should be (0)
   }
}
    "card returnValueInt(8) " should{
     "return 1 " in {
    card.returnValueInt("8") should be (1)
   }
}
    "card returnValueInt(Queen) " should{
     "return 5 " in {
    card.returnValueInt("Queen") should be (5)
   }
}
    "is Higher(Ace,Queen) " should{
     "return 5 " in {
    card.isHigher("Ace","Queen") should be (true)
   }
}
  }

