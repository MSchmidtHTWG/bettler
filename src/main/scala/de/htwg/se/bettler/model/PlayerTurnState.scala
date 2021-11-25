package de.htwg.se.bettler
package model

case class PlayerTurnState(currentPlayer : Int, maxPlayers : Int) extends State:
    def handle(e: Event) : State =
        e match
            case Event.Skip => 
                if currentPlayer + 1 >= maxPlayers then
                    val nextPlayer = 0
                    return PlayerTurnState(nextPlayer, maxPlayers)
                else
                    val nextPlayer = currentPlayer + 1
                    return PlayerTurnState(nextPlayer, maxPlayers)
            case Event.Start => PlayerTurnState(0,maxPlayers)
            case _ => this