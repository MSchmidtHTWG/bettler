package de.htwg.se.bettler
package model
import scala.swing.event.Event

class StartEvent extends Event
class SkipEvent extends Event
class GameChanged extends Event

enum Events(name : String):
    override def toString = name
    case Start extends Events("start")
    case Skip extends Events("skip")
    case Finished extends Events("finished")