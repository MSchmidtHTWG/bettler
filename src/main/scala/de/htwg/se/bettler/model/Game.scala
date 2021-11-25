package de.htwg.se.bettler
package model

case class Game(players : Vector[Cards], board : Cards, msg : String):
    def play(cards : Cards) : Game =
        if GameStateContext.state.isInstanceOf[PlayerTurnState] then
            println("test3")
            val currentPlayer = GameStateContext.state.asInstanceOf[PlayerTurnState].currentPlayer
            val playerCards = players(currentPlayer)
            if playerCards.contains(cards) && cards.isPlayable && board.isWorse(cards) then
                println("test4")
                val newPlayerCards = playerCards.remove(cards)
                val newPlayers = players.updated(currentPlayer, newPlayerCards)
                val newBoard = cards
                GameStateContext.handle(Event.Skip)
                return copy(players = newPlayers, board = newBoard, msg = "Player 2 turn.")
            return copy(msg = "Cards are not playable.")
        return copy(msg = "It is not a players turn right now.")

    def skip() : Game =
        if GameStateContext.getState().isInstanceOf[PlayerTurnState] then
            GameStateContext.handle(Event.Skip)
        return this
    
    def newGame() : Game =
        GameStateContext.setState(StartState())
        return Game()
    
    def start() : Game = 
        GameStateContext.handle(Event.Start)
        this

    override def toString : String =
        val field = Field(this)
        return field.printField() + field.eol + msg

object Game:
        def apply() : Game =
            val d = Deck(32)
            val board = Cards(Set.empty[Card])
            val s1 = Cards(d.draw())
            val s2 = Cards(d.draw())
            return Game(Vector(s1,s2), board, "Player 1 turn.")
    

