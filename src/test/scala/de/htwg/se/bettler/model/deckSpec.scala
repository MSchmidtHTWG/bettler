package de.htwg.se.bettler
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalactic.Prettifier.default
class deckSpec extends AnyWordSpec {
   "Deck" should {
      var deck = Deck(32)
      var deck2 = Deck(32)
      "Have a Method for drawning from a deck" in {
         deck.draw().size should be (7)
      }
      "should be subset" in {
      deck.draw() subsetOf deck2.deck should be (true)
      }
   }
}