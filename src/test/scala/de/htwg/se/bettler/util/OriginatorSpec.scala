package de.htwg.se.bettler
package util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import de.htwg.se.bettler.model.gameComponent.Game
import de.htwg.se.bettler.model.gameComponent.pvpGameImpl.PvPGame
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.StartState
import de.htwg.se.bettler.model.stateComponent.GameStateContext

class OriginatorSpec extends AnyWordSpec with Matchers:
    val game = PvPGame()
    val originator = new Originator {
        override def save() : Memento = GameMemento(game, StartState())
        override def restore(m : Memento) : Game = 
            GameStateContext.setState(m.state())
            m.game()
    }
    "An originator" should {
        "have a function to save a game and a state in an memento" in {
            val memento = originator.save()
            memento.game().equals(game) should be(true)
            memento.state().isInstanceOf[StartState] should be(true)
        }
        "have a function to restore a state from a memento and return a saved game" in {
            val memento = originator.save()
            val savedgame = originator.restore(memento)
            savedgame.equals(game) should be(true)
            GameStateContext.state.isInstanceOf[StartState] should be(true)
        }
    }