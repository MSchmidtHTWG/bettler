package de.htwg.se.bettler
package util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObserverSpec extends AnyWordSpec with Matchers {
  "An obersvable" should {
    "have a function to add an observer" in {
      
    }
  }

  "An observable" when {
    "adding a new observer" should {
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
    "notifying observers" should {
      "update all observers" in {
        var updated = false
        val observable = new Observable {}
        val observer = new Observer {
          override def update: Unit = updated = true
        }
        observable.add(observer)
        observable.notifyObservers
        updated shouldBe(true)
      }
    }
  }

  "An Observer" should {
    "have a funtion update which calls a function update for each observable" in {
      var updated = false
      val observer = new Observer {
        override def update: Unit = updated = true
      }
      observer.update
      updated shouldBe(true)
    }
  }
}