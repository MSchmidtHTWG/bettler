package de.htwg.se.bettler
package util

import de.htwg.se.bettler.model.Game

trait Originator:
    def save() : Memento
    def restore(m : Memento) : Game
