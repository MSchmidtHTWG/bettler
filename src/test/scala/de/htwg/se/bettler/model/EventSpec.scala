package de.htwg.se.bettler
package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class EventSpec extends AnyWordSpec with Matchers {
    "An event" when {
        "returning a string representation" should {
            "return 'start' if its a Start event" in {
                Event.Start.toString shouldBe("start")
            }
            "return 'skip' if its a Skip event" in {
                Event.Skip.toString shouldBe("skip")
            }
        }
    }
}