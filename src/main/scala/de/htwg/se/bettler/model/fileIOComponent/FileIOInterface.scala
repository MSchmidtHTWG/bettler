package de.htwg.se.bettler
package model
package fileIOComponent

import de.htwg.se.bettler.model.gameComponent.Game


trait FileIOInterface:
  def load: Game
  def save(game: Game): Unit