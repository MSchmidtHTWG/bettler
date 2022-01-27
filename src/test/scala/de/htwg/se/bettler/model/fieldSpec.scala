package de.htwg.se.bettler
package model



import cardComponent.cardBaseImpl.Cards
import gameComponent.pvpGameImpl.PvPGame
import cardComponent.cardBaseImpl.Card
import stateComponent.stateBaseImpl.PlayerTurnState


import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalactic.Prettifier.default
import de.htwg.se.bettler.model.cardComponent.CardInterface
import de.htwg.se.bettler.model.cardComponent.Value
import de.htwg.se.bettler.model.cardComponent.Symbol
import de.htwg.se.bettler.model.stateComponent.GameStateContext


class FieldSpec extends AnyWordSpec:
  "Field" should {
    val game = PvPGame(Vector.empty[Cards], Cards(Set.empty[CardInterface]), "")
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
      var board = Set.empty[CardInterface]
      var player1 = Set.empty[CardInterface]
      var player2 = Set.empty[CardInterface]
      board = board + Card(Symbol.Hearts, Value.Seven)
      player2 = player2 + Card(Symbol.Hearts, Value.Nine)
      player1 = player1 + Card(Symbol.Diamonds, Value.Ten)
      GameStateContext.setState(PlayerTurnState(0, 2))
      val game2 = PvPGame(Vector(Cards(player1), Cards(player2)), Cards(board), "")
      val field2 = Field(game2)
      field2.printField() should be(field2.eol + field2.bar() + "Player 1" + field2.eol + "[D10]" + field2.eol + field2.bar() + "Player 2" + field2.eol + "[H9]" + field2.eol + field2.bar() + "[H7]" + field2.eol + field2.bar())
    }
  }







