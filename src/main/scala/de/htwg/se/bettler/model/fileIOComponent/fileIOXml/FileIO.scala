package de.htwg.se.bettler
package model
package fileIOComponent
package fileIOXml

import com.google.inject.Guice
import com.google.inject.Inject

import scala.xml.{NodeSeq, PrettyPrinter}
import de.htwg.se.bettler.model.gameComponent.Game
import de.htwg.se.bettler.model.cardComponent.CardInterface
import de.htwg.se.bettler.model.cardComponent.CardsInterface
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl.Cards
import de.htwg.se.bettler.model.gameComponent.pvpGameImpl.PvPGame
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl.Card
import scala.util.Success
import scala.util.Failure

class FileIO extends FileIOInterface:
    override def load: Game =
        val file = scala.xml.XML.loadFile("game.xml") 
        var cards1 : CardsInterface = Cards(Set.empty[CardInterface])
        var cards2 : CardsInterface = Cards(Set.empty[CardInterface])
        var board = Cards(Set.empty[CardInterface])
        val message = (file \\ "game" \ "message" \ "@message")
        val player1 = (file \\ "game" \ "player1")
        val player2 = (file \\ "game" \ "player2")
        val p1 = player1.text.split(" ")
        val p2 = player2.text.split(" ")
        println(p1(1))
        for (i <- 0 to p1.size - 1)
            Card(p1(i)) match
                case Success(c) => cards1 = cards1.add(c)
                case Failure(f) => println("test")
        for (i <- 0 to p2.size - 1)
            Card(p2(i)) match
                case Success(c) => cards2 = cards2.add(c)
                case Failure(f) => println("test")
        return PvPGame(Vector(cards1, cards2), board, message.text)

    override def save(game: Game): Unit =
        import java.io._
        val pw = new PrintWriter(new File("game.xml"))
        val prettyPrinter = new PrettyPrinter(120, 4)
        val xml = prettyPrinter.format(gameToXML(game))
        pw.write(xml)
        pw.close
    def gameToXML(game : Game) =
        <game>
            <player1>
                {
                   for (cp<-game.getPlayers()(0).returnSet) yield cp.toString + " "
                }
            </player1>
            <player2>
                {
                    for (cp<-game.getPlayers()(1).returnSet) yield cp.toString + " "
                }
            </player2>
            <board>
                {
                    for (cp<-game.getBoard().returnSet) yield cp.toString + " "
                }
            </board>
            <message message= {game.getMessage()}> </message>
        </game>

    def cardToXML(c : CardInterface) =
        <card card= {c.toString()}>
        </card>

    def playerToXML(p : CardsInterface) =
        {
            for c <- p.returnSet yield cardToXML(c)
        }