package de.htwg.se.bettler
package model
package fileIOComponent
package fileIOJson

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.bettler.model.gameComponent.Game
import de.htwg.se.bettler.model.stateComponent.GameStateContext
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.PlayerTurnState
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.StartState

class FileIOJSpec extends AnyWordSpec:
    val fileIO = FileIO()
    val game = Game("pvp")
    "Class FileIO with JSON implementation" should {
        "save a game and a state in game.json" in {
            GameStateContext.setState(PlayerTurnState(0,2))
            fileIO.save(game)
        }
        "load a game and a state from game.json" in {
            GameStateContext.setState(StartState())
            val loadgame = fileIO.load
            GameStateContext.state.isInstanceOf[PlayerTurnState] should be(true)
            loadgame.getBoard().equals(game.getBoard()) should be(true)
        }
    }