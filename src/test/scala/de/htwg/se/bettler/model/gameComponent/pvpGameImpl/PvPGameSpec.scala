package de.htwg.se.bettler
package model
package gameComponent
package pvpGameImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.bettler.util.GameMemento
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl.Cards
import de.htwg.se.bettler.model.stateComponent.GameStateContext
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.PlayerTurnState
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.StartState
import de.htwg.se.bettler.model.stateComponent.stateBaseImpl.FinishedState

class PvPGameSpec extends AnyWordSpec {
    val game = PvPGame()
    GameStateContext.setState(PlayerTurnState(0,2))
    "A PvPGame" should {
        "have a factory method that creates a random new PvPGame with 2 players" in {
            val testgame = PvPGame.apply()
            game.getBoard().size shouldBe(0)
            game.getPlayers().foreach(x => x.size shouldBe(7))
            game.getPlayers().size should be(2)
        }
        "have a method to play cards, returning the changed game or itself, should the cards not be playable or should the state not be a playerturnstate" in {
            val gamePlayed = game.play(Cards(Set(game.getPlayers()(0).returnSet.head)))
            gamePlayed.getBoard().size should be(1)
            gamePlayed.getPlayers()(0).size should be(6)
            gamePlayed.getPlayers()(0).contains(gamePlayed.getBoard()) should be(false)
            gamePlayed.getMessage().equals("Player 2 turn.") should be(true)

            GameStateContext.setState(PlayerTurnState(0,2))
            val gameCantPlay = game.play(Cards(Set(game.getPlayers()(1).returnSet.head)))
            gameCantPlay.getMessage().equals("Cards are not playable.") should be(true)

            GameStateContext.setState(StartState())
            val gameNotAPlayersTurn = game.play(Cards(Set(game.getPlayers()(0).returnSet.head)))
            gameNotAPlayersTurn.getMessage().equals("It is not a players turn right now.") should be(true)
        }
        "have a method to skip a turn should a player not want to play something. It returns itself with a new message. If it is indeed a players turn, it changes the state to signal the next players turn" in {
            GameStateContext.setState(PlayerTurnState(0,2))
            val gameSkipTurn = game.skip()
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer should be(1)
            gameSkipTurn.getMessage().equals("Player 2 turn.") should be(true)
            GameStateContext.setState(StartState())
            val gameSkipNotAPlayersTurn = game.skip()
            gameSkipNotAPlayersTurn.equals(game) should be(true)
        }
        "have a method nextround, returning a new game starting with the loser, if in a finishedstate. The loser exchanges his best card for the winners worst card automatically" in {
            GameStateContext.setState(FinishedState(0,1))
            val nextRound = game.nextRound
            GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer should be(1)
        }
        "have a method to return a vector of cards representing the players hands" in {
            game.getPlayers().size should be(2)
        }
        "have a method to return cards, representing the board" in {
            game.getBoard().size should be(0)
        }
        "have a method to return a message to display the games state and errors" in {
            game.getMessage().equals("Player 1 turn.") should be(true)
        }
        "have a method save to make a single savegame by returning a memento of itself" in {
            GameStateContext.setState(PlayerTurnState(0,2))
            val saveGame = game.save()
            saveGame.game().equals(game) should be(true)
            saveGame.state().isInstanceOf[PlayerTurnState] should be(true)
        }
        "have a method to restore a state from a saved memento and returning the saved game" in {
            GameStateContext.setState(PlayerTurnState(0,2))
            val saveGame = game.save()
            GameStateContext.setState(StartState())
            val restoredGame = game.restore(saveGame)
            restoredGame.equals(game) should be(true)
            GameStateContext.state.isInstanceOf[PlayerTurnState] should be(true)
        }
    }
}