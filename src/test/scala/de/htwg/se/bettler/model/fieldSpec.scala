package de.htwg.se.bettler
package model

import Field._
import Card._



import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalactic.Prettifier.default

class FieldSpec extends AnyWordSpec {


  "Field" should {

    "have a bar as String of Form '--------------------------------------------------'" in {
      Field.bar() should be ("--------------------------------------------------" + Field.eol)
    }
    "have a scalable bar" in {
      Field.bar(100) should be ("----------------------------------------------------------------------------------------------------" + Field.eol)
      Field.bar(1) should be ("-" + Field.eol)
      Field.bar(15) should be ("---------------" + Field.eol)
    }
    "have printeble cards '[H7]'" in {
      Field.printCard(Card(Symbol.Hearts, Value.Seven)) should be ("[H7]")
    }
     
    "have a printable Board" in {
      var board = Set.empty[Card]
      var player1 = Set.empty[Card]
      var player2 = Set.empty[Card]
      board = board + Card(Symbol.Hearts, Value.Seven)
      player2 = player2 + Card(Symbol.Hearts, Value.Nine)
      player1 = player1 + Card(Symbol.Diamonds, Value.Ten)
      Field.printField(board,player1,player2) should be(Field.bar() + "Spieler 1" + Field.eol + "[D10]" +Field.eol + Field.bar()+"Spieler 2" + Field.eol + "[H9]" + Field.eol+Field.bar()+ "[H7]"+Field.eol + Field.bar())
    }
 
  }
   
}







