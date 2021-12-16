package de.htwg.se.bettler
package aview

import scala.util.{Try,Success,Failure}
import scala.io.StdIn.readLine
import de.htwg.se.bettler.controller.controllerBaseImp.Controller
import util.Observer
import model.GameStateContext
import model.cardComponent.cardBaseImpl.Card
import model.cardComponent.cardBaseImpl.Cards
import model.stateComponent.stateBaseImpl._

class TUI(controller : Controller) extends Observer:
    controller.add(this)
    def run =
        println("Willkommen zu Bettler. Tippe 'start' ein um das Spiel zu starten.")
        println("Mit 'exit' kannst du jederzeit das Spiel beenden.")
        TUI()

    override def update = 
        println(controller.toString())

    def TUI(): Unit =
        val input = readLine
        input match
            case "start pvp" => controller.doAndNotify(controller.newGame, "pvp")
            case "start pve" => controller.doAndNotify(controller.newGame, "pve")
            case "exit" => return
            case "skip" => controller.doAndNotify(controller.skip)
            case "save" => controller.addMemento()
            case "restore" => controller.restore
            case "undo" => controller.undo
            case "redo" => controller.redo
            case _ =>
                if input.startsWith("play") then
                    if !GameStateContext.getState().isInstanceOf[PlayerTurnState] then
                        println("Start a game first.")
                    else
                        val s = input.split(" ")
                        var l = Set.empty[Card]
                        for (i <- 1 to s.size - 1)
                            Card(s(i)) match
                                case Success(c) => 
                                    l = l + c
                                case Failure(f) => println(f.getMessage)
                        controller.doAndNotify(controller.play, Cards(l))
                else
                    println("Unknown command.")
        TUI()