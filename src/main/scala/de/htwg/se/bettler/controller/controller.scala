package de.htwg.se.bettler
package controller

import model.Field
import util.Observable

case class Controller(var field : Field) extends Observable:
    override def toString = field.toString
    def handle(input : String): Unit =
        println(input)