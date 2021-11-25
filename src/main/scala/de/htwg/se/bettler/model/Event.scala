package de.htwg.se.bettler
package model

enum Event(name : String):
    override def toString = name
    case Start extends Event("start")
    case End extends Event("end")
    case Play extends Event("play")
    case Skip extends Event("skip")
    case Exit extends Event("exit")