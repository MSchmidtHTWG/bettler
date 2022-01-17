package de.htwg.se.bettler
package util

import model.stateComponent.stateBaseImpl._
import model.gameComponent.pvpGameImpl._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MementoSpec extends AnyWordSpec with Matchers:
    "A GameMemento" should {
        "create a new GameMemento with a saved state and game which can be returned" in {
            val game = PvPGame()
            val gameMemento = GameMemento(game, StartState())
            gameMemento.savestate should be (StartState())
            gameMemento.savegame should be (game)
        }
    }