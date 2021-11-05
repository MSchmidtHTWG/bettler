package scala
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class cardSpec extends AnyWordSpec{

  "Card" should {
  


    "have a return value for returnValueInt" in {
    card.returnValueInt("Ace") should be (7)
    card.returnValueInt("7") should be (0)
    card.returnValueInt("Queen") should be (5)
   }

  
    "Have a true/false return value for isHigher " in{
    card.isHigher("Ace","Queen") should be (true)
    card.isHigher("Queen","Ace") should be (false)
   }

    "have a Methode isCard that returns if a something is a Card" in {
    card.isCard('D',"Queen") should be (true)
    card.isCard('7',"Queen") should be (false)
    card.isCard('5',"asjdfo") should be (false)

}
  
}

}