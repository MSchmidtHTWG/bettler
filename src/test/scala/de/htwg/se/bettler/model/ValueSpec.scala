package de.htwg.se.bettler
package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ValueSpec extends AnyWordSpec with Matchers {
    "A value" when {
        "returning a value" should {
            "return an Integer" in {
                Value.Seven.getValue shouldBe(7)
                Value.Eight.getValue shouldBe(8)
                Value.Nine.getValue shouldBe(9)
                Value.Ten.getValue shouldBe(10)
                Value.Jack.getValue shouldBe(11)
                Value.Queen.getValue shouldBe(12)
                Value.King.getValue shouldBe(13)
                Value.Ace.getValue shouldBe(14)
                Value.Empty.getValue shouldBe(0)
                Value.Seven.toString shouldBe("7")
            }
        }
    }
}