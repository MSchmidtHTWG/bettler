package de.htwg.se.bettler
package model

case class PlayerTurnState(currentPlayer : Int, maxPlayers : Int) extends State:
    def handle(e: Event) : State =
        e match
            case Event.Skip => 
                val nextPlayer = currentPlayer + 1
                if currentPlayer + 1 >= maxPlayers then
                    return PlayerTurnState(0, maxPlayers)
                return PlayerTurnState(nextPlayer, maxPlayers)
            case Event.Start => PlayerTurnState(0, maxPlayers)
            case _ => this