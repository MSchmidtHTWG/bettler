package de.htwg.se.bettler
package model

import de.htwg.se.bettler.util._

case class PvEGame(players : Vector[Cards], board : Cards, msg : String) extends Game:
    def play(cards : Cards) : Game =
        if GameStateContext.state.isInstanceOf[PlayerTurnState] then
            val currentPlayer = GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer
            val playerCards = players(currentPlayer)
            if playerCards.contains(cards) && cards.isPlayable && board.isWorse(cards) then
                val newPlayerCards = playerCards.remove(cards)
                val newPlayers = players.updated(currentPlayer, newPlayerCards)
                val newBoard = cards
                GameStateContext.handle(Event.Skip)
                val currentAiPlayer = GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer
                val aiPlayerCards = players(currentAiPlayer)
                for (aiPlayerCard <- aiPlayerCards.returnSet)
                    if newBoard.isWorse(Cards(Set(aiPlayerCard))) then
                        val newAiPlayerCards = aiPlayerCards.remove(Cards(Set(aiPlayerCard)))
                        val newAiBoard = Cards(Set(aiPlayerCard))
                        val newAiPlayers = newPlayers.updated(currentAiPlayer, newAiPlayerCards)
                        GameStateContext.handle(Event.Skip)
                        return copy(players = newAiPlayers, board = newAiBoard, msg= "Player 1 turn.")
                GameStateContext.handle(Event.Skip)
                return copy(players = newPlayers, board = newBoard, msg = "Player 1 turn.")
            return copy(msg = "Cards are not playable.")
        return copy(msg = "It is not a players turn right now.")

    

    def skip() : Game =
        if GameStateContext.getState().isInstanceOf[PlayerTurnState] then
            GameStateContext.handle(Event.Skip)
        return copy(board = Cards(Set.empty[Card]), msg = "Player " + (GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer + 1) + " turn.")
    
    def newGame() : Game =
        GameStateContext.setState(StartState())
        return PvEGame()

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
        def apply() : Game =
            val d = Deck(32)
            val board = Cards(Set.empty[Card])
            val s1 = d.draw()
            val s2 = d.draw()
            //GameStateContext.handle(Event.Start)
            return PvEGame(Vector(s1,s2), board, "Player 1 turn.")