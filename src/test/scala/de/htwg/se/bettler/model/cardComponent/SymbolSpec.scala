package de.htwg.se.bettler
package model
package cardComponent

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SymbolSpec extends AnyWordSpec with Matchers:
    "Symbols " should {
        "Have a function to return the String Reparsentation of a Symbol" in {
            Symbol.Hearts.toString shouldBe("H")
            Symbol.Clubs.toString shouldBe("C")
            Symbol.Diamonds.toString shouldBe("D")
            Symbol.Spades.toString shouldBe("S")
        }
    }

