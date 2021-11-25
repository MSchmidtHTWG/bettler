package de.htwg.se.bettler
package util

trait Caretaker:
    val stack=scala.collection.mutable.Stack[Memento]();
    def addMemento() : Unit
    def getMemento() : Memento