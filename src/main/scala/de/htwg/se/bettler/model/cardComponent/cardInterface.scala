package de.htwg.se.bettler
package model
package cardComponent

import model.Symbol
import scala.annotation.meta.setter
import scala.util.Try
import scala.util.Failure
import scala.util.Success
import javax.imageio.ImageIO
import java.io.File
import java.awt.Image
import java.nio.file.Paths

trait CardInterface:
    def image:File
    def sameValue(card : CardInterface):Boolean
    def isHigher(card : CardInterface):Boolean