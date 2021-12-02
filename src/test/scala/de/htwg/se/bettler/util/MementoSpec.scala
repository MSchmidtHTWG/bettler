package de.htwg.se.bettler
package util

import model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MementoSpec extends AnyWordSpec with Matchers {
    "A GameMemento" when {
        "create a new GameMemento" should {
            val game = Game()
            val gameMemento = GameMemento(game, StartState())
            "have a savestate" in {
                gameMemento.savestate should be (StartState())
            }
            "have a savegame" in {
                gameMemento.savegame should be (game)
            }
        }
    }
}