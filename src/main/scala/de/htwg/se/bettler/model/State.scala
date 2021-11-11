package de.htwg.se.bettler
package model

trait State:
    def handle(input : String) : Unit

