package de.htwg.se.bettler
package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SymbolSpec extends AnyWordSpec with Matchers {
    "A symbol" when {
        "using toString" should {
            "return a string representation of the symbol" in {
                Symbol.Hearts.toString shouldBe("H")
            }
        }
    }
}
