package de.htwg.se.bettler
package util

trait Command:
    def doStep: Unit
    def undoStep: Unit
    def redoStep: Unit