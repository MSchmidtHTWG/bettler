package de.htwg.se.bettler
package model

import Field._
import Card._



import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalactic.Prettifier.default

class FieldSpec extends AnyWordSpec {
  "Field" should {
    val game = PvPGame(Vector.empty[Cards], Cards(Set.empty[Card]), "")
    val field = Field(game)
    "have a scalable bar" in {
      field.bar() should be ("--------------------------------------------------" + field.eol)
      field.bar(100) should be ("----------------------------------------------------------------------------------------------------" + field.eol)
      field.bar(1) should be ("-" + field.eol)
      field.bar(15) should be ("---------------" + field.eol)
    }
    "have a method to print a single Card" in {
      field.printCard(Card(Symbol.Hearts, Value.Seven)) should be ("[H7]")
    }
     
    "have a printable Board" in {
      var board = Set.empty[Card]
      var player1 = Set.empty[Card]
      var player2 = Set.empty[Card]
      board = board + Card(Symbol.Hearts, Value.Seven)
      player2 = player2 + Card(Symbol.Hearts, Value.Nine)
      player1 = player1 + Card(Symbol.Diamonds, Value.Ten)
      GameStateContext.setState(PlayerTurnState(0, 2))
      val game2 = PvPGame(Vector(Cards(player1), Cards(player2)), Cards(board), "")
      val field2 = Field(game2)
      field2.printField() should be(field2.eol + field2.bar() + "Player 1" + field2.eol + "[D10]" + field2.eol + field2.bar() + "Player 2" + field2.eol + "[H9]" + field2.eol + field2.bar() + "[H7]" + field2.eol + field2.bar())
    }
  }
}







