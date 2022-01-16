package de.htwg.se.bettler
package model
package gameComponent
package pveGameImpl

import cardComponent._
import fieldComponent._
import model.cardComponent.cardBaseImpl.Deck
import model.cardComponent.cardBaseImpl.Cards
import model.cardComponent.cardBaseImpl.Card
import de.htwg.se.bettler.util._
import stateComponent.stateBaseImpl._
import fieldComponent.fieldBaseImpl.Field
import fieldComponent.fieldBeautifulImpl._
import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}

case class PvEGame @Inject()(players : Vector[CardsInterface], board : CardsInterface, msg : String) extends Game:
    var field : FieldInterface = Field(this)
    def setBeautifulField : Unit = field = Field2(this)
    def play(cards : CardsInterface) : Game =
        if GameStateContext.state.isInstanceOf[PlayerTurnState] then
            val currentPlayer = GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer
            val playerCards = players(currentPlayer)
            if playerCards.contains(cards) && cards.isPlayable && board.isWorse(cards) then
                val newPlayerCards = playerCards.remove(cards)
                val newPlayers = players.updated(currentPlayer, newPlayerCards)
                val newBoard = cards
                if newPlayerCards.size == 0 then
                    GameStateContext.handle(Events.Finished)
                    return copy(players = newPlayers, board = newBoard, msg = "Player " + (currentPlayer + 1) + " has won the game.")
                GameStateContext.handle(Events.Skip)
                val currentAiPlayer = GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer
                val aiPlayerCards = players(currentAiPlayer)
                val aiCardsToPlay = aiPlayerCards.findPlayable(newBoard)
                aiCardsToPlay match
                    case Some(c) => 
                        val newAiPlayerCards = aiPlayerCards.remove(c)
                        val newAiBoard = c
                        val newAiPlayers = newPlayers.updated(currentAiPlayer, newAiPlayerCards)
                        if newAiPlayerCards.size == 0 then
                            GameStateContext.handle(Events.Finished)
                            return copy(players = newPlayers, board = newBoard, msg = "Player " + (currentAiPlayer + 1) + " has won the game.")
                        GameStateContext.handle(Events.Skip)
                        return copy(players = newAiPlayers, board = newAiBoard, msg= "Player 1 turn.")
                    case None => 
                        GameStateContext.handle(Events.Skip)
                        return copy(players = newPlayers, board = Cards(Set.empty[CardInterface]), msg = "Player 1 turn.")
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
                    GameStateContext.handle(Events.Finished)
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
        val newMsg = "Player " + loserIndex + " turn."
        newPlayers = newPlayers.updated(loserIndex, newLoserCards)
        GameStateContext.handle(Events.Start)
        return PvEGame(newPlayers, newGame.getBoard(), newMsg)

    def getPlayers() = players
    def getBoard() = board
    def getMessage() = msg

    def save() : Memento = GameMemento(this, GameStateContext.getState())
    def restore(m : Memento) : Game = 
        GameStateContext.setState(m.state())
        m.game()

    override def toString : String =
        return field.printField() + field.eol + msg


object PvEGame:
        def apply() : PvEGame =
            val d = Deck(32)
            val board = Cards(Set.empty[CardInterface])
            val s1 = d.draw()
            val s2 = d.draw()
            //GameStateContext.handle(Event.Start)
            return PvEGame(Vector(s1,s2), board, "Player 1 turn.")