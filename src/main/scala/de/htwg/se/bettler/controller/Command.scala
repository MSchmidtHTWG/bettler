package de.htwg.se.bettler
package controller

trait Command:
    def doStep: Unit
    def undoStep: Unit
    def redoStep: Unit