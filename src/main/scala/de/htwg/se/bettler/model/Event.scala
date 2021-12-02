package de.htwg.se.bettler
package model

enum Event(name : String):
    override def toString = name
    case Start extends Event("start")
    case Skip extends Event("skip")