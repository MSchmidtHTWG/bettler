package de.htwg.se.bettler
package model
package gameComponent
package pvpGameImpl

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

case class PvPGame @Inject()(players : Vector[CardsInterface], board : CardsInterface, msg : String) extends Game:
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
                else
                    GameStateContext.handle(GameStateEvents.Skip)
                    return copy(players = newPlayers, board = newBoard, msg = "Player " + (GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer + 1) + " turn.")
            return copy(msg = "Cards are not playable.")
        return copy(msg = "It is not a players turn right now.")

    def skip() : Game =
        if GameStateContext.getState().isInstanceOf[PlayerTurnState] then
            GameStateContext.handle(GameStateEvents.Skip)
            return copy(board = Cards(Set.empty[CardInterface]), msg = "Player " + (GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer + 1) + " turn.")
        return this

    def nextRound : Game =
        if !GameStateContext.getState().isInstanceOf[FinishedState] then
            return this
        val newGame = PvPGame()
        val winnerIndex = GameStateContext.state.asInstanceOf[FinishedState].winner
        val loserIndex = GameStateContext.state.asInstanceOf[FinishedState].loser
        val winnerCards = newGame.getPlayers()(winnerIndex)
        val loserCards = newGame.getPlayers()(loserIndex)
        val winnerWorstCard = winnerCards.worstCards
        val loserBestCard = loserCards.bestCards
        val newWinnerCards = winnerCards.remove(winnerWorstCard).add(loserBestCard)
        val newLoserCards = loserCards.remove(loserBestCard).add(winnerWorstCard)
        var newPlayers = newGame.getPlayers().updated(winnerIndex, newWinnerCards).updated(loserIndex, newLoserCards)
        val newMsg = "Player " + (loserIndex + 1) + " gave Player " + (winnerIndex + 1) + " " + loserBestCard.toString + " and received " + winnerWorstCard.toString + ".\n" + "Player " + (loserIndex + 1) + " turn."
        GameStateContext.handle(GameStateEvents.Start)
        return PvPGame(newPlayers, newGame.getBoard(), newMsg)

        

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

object PvPGame:
        def apply() : PvPGame =
            val d = Deck(32)
            val board = Cards(Set.empty[CardInterface])
            val s1 = d.draw()
            val s2 = d.draw()
            return PvPGame(Vector(s1,s2), board, "Player 1 turn.")