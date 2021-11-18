package de.htwg.se.bettler
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class gameSpec extends AnyWordSpec {
    "Game" should {
        "should create a new Game with 2 players, each with 7 Cards and an empty field." in {
            val game = Game()
            game.deck.size shouldBe(18)
            game.spieler1.cards.size shouldBe(7)
            game.spieler2.cards.size shouldBe(7)
            game.spielfeld.cards.size shouldBe(0)
            game.msg shouldBe("Spieler 1 ist dran.")
            game.state.isInstanceOf[P1TurnState] shouldBe(true)
        }
    }
}