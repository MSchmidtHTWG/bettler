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
    "have printeble cards '[H,7]]'" in {
      Field.printCard(card) should be ("[H,7]")
    }
     
    "have a printable Board" in {
      Field.printField(board1,player1,player2) should be(Field.bar() + "Spieler 1" + Field.eol + "[H,7][S,ACE]" +Field.eol + Field.bar()+"Spieler 2" + Field.eol + "[H,9][C,J]" + Field.eol+Field.bar()+ "[D,10][C,7]"+Field.eol + Field.bar())
    }
 
  }
   
}







