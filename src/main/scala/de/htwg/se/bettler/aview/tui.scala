package de.htwg.se.bettler
package aview

import scala.io.StdIn.readLine
import controller.Controller
import util.Observer
import model.Card

class TUI(controller : Controller) extends Observer:
    controller.add(this)
    def run =
        println("Willkommen zu Bettler. Tippe 'start' ein um das Spiel zu starten.")
        println("Mit 'exit' kannst du jederzeit das Spiel beenden.")
        TUI()

    override def update = 
        println(controller.toString())

    def TUI(): Unit =
        val input = readLine;
        if input == "start" then controller.start()
        if input == "exit" then return
        if input == "skip" then controller.skip()
        val s = input.split(" ")
        var cards = Set.empty[Card]
        if (s(0) == "play" && s.size > 1) then {
            for (i <- 1 to s.size - 1) {
                cards = cards + Card.returnCard(s(i))
            }
            controller.play(cards)
        }
        //controller.handle(input)
        TUI()