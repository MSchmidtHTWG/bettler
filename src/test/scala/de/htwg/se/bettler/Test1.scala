import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class Test1 extends AnyWordSpec {


 "Main" should{
   "have a bar as String of Form '+----+----+----+----+----+----+----+'" in {
    bar() should be ("+----+----+----+----+----+----+----+" + eol)
   }
 
 } 
}