package de.htwg.se.bettler
package model
package stateComponent
package stateBaseImpl

case class PlayerTurnState(currentPlayer : Int, maxPlayers : Int) extends State:
    def handle(event: GameStateEvents) : State =
        event match
            case GameStateEvents.Skip => 
                val nextPlayer = currentPlayer + 1
                if currentPlayer + 1 >= maxPlayers then
                    return PlayerTurnState(0, maxPlayers)
                return PlayerTurnState(nextPlayer, maxPlayers)
            case GameStateEvents.Start => PlayerTurnState(0, maxPlayers)
            case GameStateEvents.Finished =>
                var loser = currentPlayer + 1
                if loser >= maxPlayers then
                    loser = 0
                FinishedState(currentPlayer, loser)