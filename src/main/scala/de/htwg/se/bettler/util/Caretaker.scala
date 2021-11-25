package de.htwg.se.bettler
package util

trait Caretaker:
    var stack=scala.collection.mutable.Stack[Memento]();
    def addMemento() : Unit
    def getMemento() : Memento