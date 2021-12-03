package de.htwg.se.bettler
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.bettler.util.GameMemento

class PvPGameSpec extends AnyWordSpec {
    "A PvPGame" should {
        "have a factory method that creates a random new PvEGame with 2 players" in {
            val game = PvPGame()
            game.getBoard().size shouldBe(0)
            game.getPlayers().foreach(x => x.size shouldBe(7))
        }
    }
}