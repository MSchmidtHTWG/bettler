package de.htwg.se.bettler
package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model._

class controllerSpec extends AnyWordSpec {
    "Controller" should {
        "have a method play, that returns a Game" in {
            val game = Game()
            val controller = Controller(game)
            controller.game.deck.equals(game.deck) shouldBe(true)
        }
    }
}