package de.htwg.se.bettler
package util

import de.htwg.se.bettler.model.gameComponent.Game

trait Originator:
    def save() : Memento
    def restore(m : Memento) : Game
