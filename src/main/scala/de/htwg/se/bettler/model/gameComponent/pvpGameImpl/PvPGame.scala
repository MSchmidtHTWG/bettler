package de.htwg.se.bettler
package model
package gameComponent
package pvpGameImpl

import cardComponent.cardBaseImpl.Cards
import cardComponent.cardBaseImpl.Card
import model.cardComponent.cardBaseImpl.Deck
import de.htwg.se.bettler.util._
import stateComponent.stateBaseImpl._
import fieldComponent.fieldBaseImpl._

case class PvPGame(players : Vector[CardsInterface], board : CardsInterface, msg : String) extends Game:
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
                else
                    GameStateContext.handle(Events.Skip)
                    return copy(players = newPlayers, board = newBoard, msg = "Player " + (GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer + 1) + " turn.")
            return copy(msg = "Cards are not playable.")
        return copy(msg = "It is not a players turn right now.")

    def skip() : Game =
        if GameStateContext.getState().isInstanceOf[PlayerTurnState] then
            GameStateContext.handle(Events.Skip)
            return copy(board = CardsInterface(Set.empty[CardInterface]), msg = "Player " + (GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer + 1) + " turn.")
        return this

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
        def apply() : Game =
            val d = DeckInterface(32)
            val board = CardsInterface(Set.empty[CardInterface])
            val s1 = d.draw()
            val s2 = d.draw()
            //GameStateContext.handle(Event.Start)
            return PvPGame(Vector(s1,s2), board, "Player 1 turn.")