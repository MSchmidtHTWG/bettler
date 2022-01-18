package de.htwg.se.bettler
package model
package gameComponent
package pveGameImpl

import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.name.Names
import de.htwg.se.bettler.util._

import cardComponent._
import model.cardComponent.cardBaseImpl.Deck
import model.cardComponent.cardBaseImpl.Cards
import model.cardComponent.cardBaseImpl.Card
import stateComponent.stateBaseImpl._
import stateComponent._


case class PvEGame @Inject()(players : Vector[CardsInterface], board : CardsInterface, msg : String) extends Game:
    def play(cards : CardsInterface) : Game =
        if GameStateContext.state.isInstanceOf[PlayerTurnState] then
            val currentPlayer = GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer
            val playerCards = players(currentPlayer)
            if playerCards.contains(cards) && cards.isPlayable && board.isWorse(cards) then
                val newPlayerCards = playerCards.remove(cards)
                val newPlayers = players.updated(currentPlayer, newPlayerCards)
                val newBoard = cards
                if newPlayerCards.size == 0 then
                    GameStateContext.handle(GameStateEvents.Finished)
                    return copy(players = newPlayers, board = newBoard, msg = "Player " + (currentPlayer + 1) + " has won the game.")
                GameStateContext.handle(GameStateEvents.Skip)
                return aiTurn(newPlayers, newBoard)
            return copy(msg = "Cards are not playable.")
        return copy(msg = "It is not a players turn right now.")

    def skip() : Game =
        if GameStateContext.getState().isInstanceOf[PlayerTurnState] then
            if GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer == 0 then
                val aiPlayerCards = players(1)
                val lowestAiCards = players(1).findPlayable(Cards(Set.empty[CardInterface]))
                val newBoard = lowestAiCards.get
                val newAiPlayerCards = aiPlayerCards.remove(lowestAiCards.get)
                val newAiPlayers = players.updated(1, aiPlayerCards.remove(newBoard))
                if newAiPlayerCards.size == 0 then
                    GameStateContext.handle(GameStateEvents.Skip)
                    GameStateContext.handle(GameStateEvents.Finished)
                    return copy(players = newAiPlayers, board = newBoard, msg = "Player 2 has won the game.")
                return copy(players = newAiPlayers, board = newBoard, msg = "Player " + (GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer + 1) + " turn.")
        return this

    def nextRound : Game =
        if !GameStateContext.getState().isInstanceOf[FinishedState] then
            return this
        val newGame = PvEGame()
        val winnerIndex = GameStateContext.state.asInstanceOf[FinishedState].winner
        val loserIndex = GameStateContext.state.asInstanceOf[FinishedState].loser
        val winnerCards = newGame.getPlayers()(winnerIndex)
        val loserCards = newGame.getPlayers()(loserIndex)
        val winnerWorstCard = winnerCards.worstCards
        val loserBestCard = loserCards.bestCards
        val newWinnerCards = winnerCards.remove(winnerWorstCard).add(loserBestCard)
        val newLoserCards = loserCards.remove(loserBestCard).add(winnerWorstCard)
        var newPlayers = newGame.getPlayers().updated(winnerIndex, newWinnerCards)
        val newMsg = "Player " + (loserIndex + 1) + " turn."
        newPlayers = newPlayers.updated(loserIndex, newLoserCards)
        GameStateContext.handle(GameStateEvents.Start)
        if (GameStateContext.getState().asInstanceOf[PlayerTurnState].currentPlayer == 1) then
            return PvEGame(newPlayers, newGame.getBoard(), newMsg).aiTurn(newPlayers, newGame.getBoard())
        return PvEGame(newPlayers, newGame.getBoard(), newMsg)

    def aiTurn(newPlayers : Vector[CardsInterface], newBoard : CardsInterface) : Game = 
        val currentAiPlayer = GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer
        val aiPlayerCards = players(currentAiPlayer)
        val aiCardsToPlay = aiPlayerCards.findPlayable(newBoard)
        aiCardsToPlay match
            case Some(c) => 
                val newAiPlayerCards = aiPlayerCards.remove(c)
                val newAiBoard = c
                val newAiPlayers = newPlayers.updated(currentAiPlayer, newAiPlayerCards)
                if newAiPlayerCards.size == 0 then
                    GameStateContext.handle(GameStateEvents.Finished)
                    return copy(players = newPlayers, board = newBoard, msg = "Player " + (currentAiPlayer + 1) + " has won the game.")
                GameStateContext.handle(GameStateEvents.Skip)
                return copy(players = newAiPlayers, board = newAiBoard, msg= "Player 1 turn.")
            case None => 
                GameStateContext.handle(GameStateEvents.Skip)
                return copy(players = newPlayers, board = Cards(Set.empty[CardInterface]), msg = "Player 1 turn.")

    def getPlayers() = players
    def getBoard() = board
    def getMessage() = msg

    def save() : Memento = GameMemento(this, GameStateContext.getState())
    def restore(m : Memento) : Game = 
        GameStateContext.setState(m.state())
        m.game()

    override def toString : String =
        val field = Field(this)
        return field.printField() + field.eol + msg


object PvEGame:
        def apply() : PvEGame =
            val d = Deck(32)
            val board = Cards(Set.empty[CardInterface])
            val s1 = d.draw()
            val s2 = d.draw()
            return PvEGame(Vector(s1,s2), board, "Player 1 turn.")