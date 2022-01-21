package de.htwg.se.bettler
package model
package fileIOComponent
package fileIOJson

import scala.io.Source
import java.io._
import java.io.PrintWriter
import de.htwg.se.bettler.model.gameComponent.Game
import de.htwg.se.bettler.model.cardComponent.CardsInterface

import play.api.libs.json._
import scala.io.Source
import de.htwg.se.bettler.model.cardComponent.CardInterface
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl.Card
import com.google.common.base.Strings
import de.htwg.se.bettler.model.gameComponent.pvpGameImpl.PvPGame
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl.Cards

class FileIOJSon extends FileIOInterface:
    override def load: Game = 
        val source: String = Source.fromFile("game.json").getLines.mkString
        val json: JsValue = Json.parse(source)
        var p1cards = Set.empty[CardInterface]
        var p2cards = Set.empty[CardInterface]
        var boardc = Set.empty[CardInterface]
        val player1 = (json \\ "player1")
        val player2 = (json \\ "player2")
        val board = (json \\ "board")
        val message = (json \ "message")
        val msg = message.as[String]
        for (p1<-player1)
            val value = (p1 \ "value").as[String]
            val symbol = (p1 \ "symbol").as[String]
            p1cards = p1cards + Card(de.htwg.se.bettler.model.cardComponent.Symbol(symbol), de.htwg.se.bettler.model.cardComponent.Value(value))
        for (p1<-player2)
            val value = (p1 \ "value").as[String]
            val symbol = (p1 \ "symbol").as[String]
            p2cards = p2cards + Card(de.htwg.se.bettler.model.cardComponent.Symbol(symbol), de.htwg.se.bettler.model.cardComponent.Value(value))
        for (p1<-board)
            val value = (p1 \ "value").as[String]
            val symbol = (p1 \ "symbol").as[String]
            boardc = boardc + Card(de.htwg.se.bettler.model.cardComponent.Symbol(symbol), de.htwg.se.bettler.model.cardComponent.Value(value))
        PvPGame(Vector(Cards(p1cards), Cards(p2cards)), Cards(boardc), msg)

    override def save(game : Game) = 
        import java.io._
        val pw = new PrintWriter(new File("game.json"))
        pw.write(Json.prettyPrint(Json.toJson[Game](game)))
        pw.close

    implicit val gameWrites : Writes[Game] = new Writes[Game] {
        def writes(game : Game):JsValue = Json.obj(
            "player1" -> Json.toJson(
                for {
                    p <- game.getPlayers()(0).returnSet
                } yield {
                    Json.obj(
                        "symbol" -> p.getSymbol.toString,
                        "value" -> p.getValue.toString
                    )
                }
            ),
            "player2" -> Json.toJson(
                for {
                    p <- game.getPlayers()(1).returnSet
                } yield {
                    Json.obj(
                        "symbol" -> p.getSymbol.toString,
                        "value" -> p.getValue.toString
                    )
                }
            ),
            "board" -> Json.toJson(
                for {
                    p <- game.getBoard().returnSet
                } yield {
                    Json.obj(
                        "symbol" -> p.getSymbol.toString,
                        "value" -> p.getValue.toString
                    )
                }
            ),
            "message" -> game.getMessage(),
        )
    }