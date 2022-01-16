package de.htwg.se.bettler
package model
package gameComponent

import cardComponent._
import de.htwg.se.bettler.util._
import pveGameImpl._
import pvpGameImpl._

trait Game extends Originator:
    def play(cards : CardsInterface) : Game
    def skip() : Game
    def getPlayers() : Vector[CardsInterface]
    def getBoard() : CardsInterface
    def getMessage() : String
    def setBeautifulField : Unit
    def nextRound : Game

object Game:
    def apply() : Game = Game("pvp")
    def apply(kind: String) : Game = kind match
        case "pvp" => 
            GameStateContext.handle(Events.Start)
            PvPGame()
        case "pve" => 
            GameStateContext.handle(Events.Start)
            PvEGame()