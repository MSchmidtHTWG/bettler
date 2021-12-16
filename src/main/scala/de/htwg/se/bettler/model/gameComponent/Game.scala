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

object Game:
    def apply() : Option[Game] = None
    def apply(kind: String) : Option[Game] = kind match
        case "pvp" => 
            GameStateContext.handle(Events.Start)
            Some(PvPGame())
        case "pve" => 
            GameStateContext.handle(Events.Start)
            Some(PvEGame())
        case _ => None