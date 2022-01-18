package de.htwg.se.bettler
package util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec



class ObserverSpec extends AnyWordSpec with Matchers:
  var updated = false
  val observable = new Observable {}
  val observer = new Observer {
    override def update: Unit = updated = true
  }
  "An observable" should {
    "have a function to add an observer" in {
      observable.add(observer)
      observable.subscribers.contains(observer) should be(true)
    }
    "have a function to remove an observer" in {
      observable.remove(observer)
      observable.subscribers.contains(observer) should be(false)
    }
    "have a function to notify each observer" in {
      observable.add(observer)
      observable.notifyObservers
      updated should be(true)
    }
  }

  "An Observer" should {
    "have a function update to change accordingly" in {
      updated = false
      observer.update
      updated shouldBe(true)
    }
  }