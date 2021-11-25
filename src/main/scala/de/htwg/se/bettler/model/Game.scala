package de.htwg.se.bettler
package model

import de.htwg.se.bettler.util._

trait Game extends Originator:
    def play(cards : Cards) : Game
    def skip() : Game
    def newGame() : Game
    def start() : Game
    def getPlayers() : Vector[Cards]
    def getBoard() : Cards
    def getMessage() : String

object GameFactory:
    def getInstance(kind: String) = kind match
        case "pvp" => PvPGame()
        case "pve" => PvEGame()