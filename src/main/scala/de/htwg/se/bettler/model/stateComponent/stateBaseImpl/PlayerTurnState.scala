package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

case class PlayerTurnState(currentPlayer : Int, maxPlayers : Int) extends State:
    def handle(e: Events) : State =
        e match
            case Events.Skip => 
                val nextPlayer = currentPlayer + 1
                if currentPlayer + 1 >= maxPlayers then
                    return PlayerTurnState(0, maxPlayers)
                return PlayerTurnState(nextPlayer, maxPlayers)
            case Events.Start => PlayerTurnState(0, maxPlayers)
            case Events.Finished =>
                var loser = currentPlayer + 1
                if loser >= maxPlayers then
                    loser = 0
                FinishedState(currentPlayer, loser)