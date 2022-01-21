package de.htwg.se.bettler
package aview
package tui

import scala.util.{Try,Success,Failure}
import scala.io.StdIn.readLine
import de.htwg.se.bettler.controller._
import util.Observer
import model.stateComponent.GameStateContext
import model.cardComponent.cardBaseImpl.Card
import model.cardComponent.cardBaseImpl.Cards
import model.cardComponent._
import model.stateComponent.stateBaseImpl._
import scala.swing.Reactor

import scala.sys.process._
import java.nio.charset.StandardCharsets

import scala.swing.Publisher
import scala.swing.event.Event

class TUI(controller : ControllerInterface) extends Observer with Reactor:
    var exit = false
    controller.add(this)
    listenTo(controller)
    reactions += {
        case e : CloseEvent => exit = true
    }
    
    def run =
        println("Willkommen zu Bettler. Tippe 'start' ein um das Spiel zu starten.")
        println("Mit 'exit' kannst du jederzeit das Spiel beenden.")
        TUI()

    override def update = 
        println(controller.toString())

    def TUI(): Unit =
        val input = readLine
        if exit then return
        input match
            case "start pvp" => controller.doAndNotify(controller.newGame, "pvp")
            case "start pve" => controller.doAndNotify(controller.newGame, "pve")
            case "exit" => controller.exit
                //System.exit(0)
            case "skip" => controller.doAndNotify(controller.skip)
            case "save" => controller.addMemento()
            case "restore" => controller.restore
            case "undo" => controller.undo
            case "redo" => controller.redo
            case "next" => controller.doAndNotify(controller.nextRound)
            case "save xml" => controller.saveXML
            case "load xml" => controller.loadXML
            case _ =>
                if input.startsWith("play") then
                    if !GameStateContext.getState().isInstanceOf[PlayerTurnState] then
                        println("Start a game first.")
                    else
                        val s = input.split(" ")
                        var l = Set.empty[CardInterface]
                        for (i <- 1 to s.size - 1)
                            Card(s(i)) match
                                case Success(c) => 
                                    l = l + c
                                case Failure(f) => println(f.getMessage)
                        controller.doAndNotify(controller.play, Cards(l))
                else
                    println("Unknown command.")
        if !exit then 
            TUI()