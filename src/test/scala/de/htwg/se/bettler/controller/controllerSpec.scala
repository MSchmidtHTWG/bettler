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
            val game2 = controller.play(Set.empty[Card])
            game2.deck.equals(game.deck) shouldBe(true)
            game2.msg == "Falsche Eingabe. Spiele Karten mit 'play Karte1 Karte2 ..'. Spieler 1 ist an der Reihe." shouldBe(true)
            val game3 = controller.play(Set(game.spieler1.cards.head))
            val game4 = Controller(Game()).play(Set(game.spieler1.cards.head))
            game3.state.isInstanceOf[P2TurnState] shouldBe(true)
            game4.state.isInstanceOf[P2TurnState] shouldBe(true)
            game4.msg == "Spieler 2 ist dran." shouldBe(true)
        }
        "have a method skip to skip a turn" in {
            val game = Game()
            val controller = Controller(game)
            val game2 = controller.skip()
            game2.state.isInstanceOf[P2TurnState] shouldBe(true)
        }
        "have a method start to start a new game" in {
            val game = Game()
            val controller = Controller(game)
            val game2 = controller.start()
            game.deck.equals(game2.deck) shouldBe(true)
        }
    }
}