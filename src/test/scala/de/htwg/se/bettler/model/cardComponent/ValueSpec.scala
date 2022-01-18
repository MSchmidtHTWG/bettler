package de.htwg.se.bettler
package model
package cardComponent

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ValueSpec extends AnyWordSpec with Matchers {

    "Value" should {
        "have a method getValue to return the value" in {
            Value.Seven.getValue shouldBe(7)
            Value.Eight.getValue shouldBe(8)
            Value.Nine.getValue shouldBe(9)
            Value.Ten.getValue shouldBe(10)
            Value.Jack.getValue shouldBe(11)
            Value.Queen.getValue shouldBe(12)
            Value.King.getValue shouldBe(13)
            Value.Ace.getValue shouldBe(14)
            Value.Empty.getValue shouldBe(0)
            
        }
        "have a method toString to return the string representation of a value" in {
            Value.Seven.toString shouldBe("7")
            Value.Eight.toString shouldBe("8")
            Value.Nine.toString shouldBe("9")
            Value.Ten.toString shouldBe("10")
            Value.Jack.toString shouldBe("J")
            Value.Queen.toString shouldBe("Q")
            Value.King.toString shouldBe("K")
            Value.Ace.toString shouldBe("A")
            Value.Empty.toString shouldBe("")
            
        }
    }
}
