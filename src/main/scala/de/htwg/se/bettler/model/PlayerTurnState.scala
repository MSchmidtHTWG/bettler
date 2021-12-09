package de.htwg.se.bettler
package model

case class PlayerTurnState(currentPlayer : Int, maxPlayers : Int) extends State:
    def handle(e: Events) : State =
        e match
            case Events.Skip => 
                val nextPlayer = currentPlayer + 1
                if currentPlayer + 1 >= maxPlayers then
                    return PlayerTurnState(0, maxPlayers)
                return PlayerTurnState(nextPlayer, maxPlayers)
            case Events.Start => PlayerTurnState(0, maxPlayers)