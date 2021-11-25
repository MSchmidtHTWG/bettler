package de.htwg.se.bettler
package aview

import scala.io.StdIn.readLine
import controller.Controller
import util.Observer
import model._

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
            case "restore" => controller.restore(controller.getMemento)
            case _ =>
                if input.startsWith("play") then
                    val s = input.split(" ")
                    var l = Set.empty[Card]
                    for (i <- 1 to s.size - 1)
                        Card.returnCard(s(i)) match
                            case Some(c) => l = l + c
                            case None => println("")
                    controller.doAndNotify(controller.play, Cards(l))
        TUI()