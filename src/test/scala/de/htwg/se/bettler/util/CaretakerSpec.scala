package de.htwg.se.bettler
package util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.bettler.model.gameComponent.Game
import de.htwg.se.bettler.model.stateComponent.State

class CaretakerSpec extends AnyWordSpec with Matchers:
    val caretaker = new Caretaker {
        override def addMemento() : Unit = stack.push(Game().save())
        override def getMemento() : Memento = stack.pop
    }
    "A caretaker" should {
        "have a function to add a memento to its stack" in {
            caretaker.addMemento()
            caretaker.stack.size should be (1)
        }
        "have a function to pop and return a memento from its stack" in {
            val memento = caretaker.getMemento()
            caretaker.stack.size should be (0)
            memento.game().isInstanceOf[Game] should be(true)
            memento.state().isInstanceOf[State] should be(true)
        }
    }