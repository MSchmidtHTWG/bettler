package scala
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalactic.Prettifier.default
class decSpec extends AnyWordSpec {

"deck" should {
  
    "have Methode for returning cards from a deck" in {
     val deck = new Deck();   
     deck.draw() should be (('H',"7")::('H',"8")::('H',"9")::('H',"10")::('H',"Jack")::('H',"Queen")::('H',"King")::Nil)
   }

    "have variable 'cards' in Form of a list of a complete deck" in {
     val deck = new Deck();   
     deck.cards should be (('H', "7") :: ('H', "8") :: ('H', "9") :: ('H', "10") :: ('H', "Jack") :: ('H', "Queen") :: ('H', "King") :: ('H', "Ace")
        :: ('S', "7") :: ('S', "8") :: ('S', "9") :: ('S', "10") :: ('S', "Jack") :: ('S', "Queen") :: ('S', "King") :: ('S', "Ace")
        :: ('D', "7") :: ('D', "8") :: ('D', "9") :: ('D', "10") :: ('D', "Jack") :: ('D', "Queen") :: ('D', "King") :: ('D', "Ace")
        :: ('C', "7") :: ('C', "8") :: ('C', "9") :: ('C', "10") :: ('C', "Jack") :: ('C', "Queen") :: ('C', "King") :: ('C', "Ace") :: Nil)
   }

}

}
