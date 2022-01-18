package de.htwg.se.bettler
package util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CommandSpec extends AnyWordSpec with Matchers:
    var counter = 0
    var redo = 0
    val command = new Command:
        def doStep: Unit = counter += 1
        def redoStep: Unit =
            val tmp = redo 
            redo = counter
            counter = tmp
        def undoStep: Unit =
            val tmp = redo 
            redo = counter
            counter = tmp
    "A command" should {
        "have a function to do a step" in {
            command.doStep
            counter should be(1)
        }
        "have a function to undo a step and save that step for redo" in {
            command.undoStep
            counter should be(0)
            redo should be(1)
        }
        "have a function to redo the last step that was undone and save that undostep for next redo" in {
            command.redoStep
            counter should be(1)
            redo should be(0)
        }
    }