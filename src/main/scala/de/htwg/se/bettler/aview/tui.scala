package de.htwg.se.bettler
package aview

import scala.io.StdIn.readLine
import controller.Controller
import util.Observer

class TUI(controller : Controller) extends Observer:
    controller.add(this)
    def run =
        println(controller.field.toString())
        TUI()
    override def update = 
        println(controller.field.toString())

    def TUI(): Unit =
        val input = readLine;
        controller.handle(input)
        TUI()