package de.htwg.se.bettler
package model
import scala.swing.event.Event

class StartEvent extends Event
class SkipEvent extends Event
class GameChanged extends Event
class CloseEvent extends Event

enum GameStateEvents(name : String):
    override def toString = name
    case Start extends GameStateEvents("start")
    case Skip extends GameStateEvents("skip")
    case Finished extends GameStateEvents("finished")