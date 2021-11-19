package de.htwg.se.bettler
package util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObserverSpec extends AnyWordSpec with Matchers {
  "An observable" when {
    "added a new observer" should {
      var updated = false
      val observable = new Observable {}
      val observer = new Observer {
        override def update: Unit = updated = true
      }

      observable.add(observer)

      "have a subscriber" in {
        observable.subscribers.contains(observer) should be(true)
      }
      "have subscriber removed" in {
        observable.remove(observer)
        observable.subscribers.contains(observer) should be(false)
      }
    }
  }
  "An Observer" should {
    "have a funtion update" in {
      var updated = false
      val observer = new Observer {
        override def update: Unit = updated = true
      }
      observer.update
      updated shouldBe(true)
    }
  }
}