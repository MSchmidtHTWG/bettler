package scala
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
class chandlerSpec extends AnyWordSpec{

  "Handler" should {

    "have a Methode for checking if something played is Card" in {
    val handler = new Handler()
    var a = Array("H,9")
    var b = Array("sahoif,asfh")
    handler.playedIsCard(a)  should be (true)
    handler.playedIsCard(b)  should be (false)
   }
}
}