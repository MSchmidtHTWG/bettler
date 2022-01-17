package de.htwg.se.bettler
package model
package cardComponent

import java.awt.Image
import java.io.File
import java.nio.file.Paths

import javax.imageio.ImageIO
import scala.annotation.meta.setter
import scala.util.Failure
import scala.util.Success
import scala.util.Try

import cardComponent.Symbol


trait CardInterface:
    def image:File
    def sameValue(card : CardInterface):Boolean
    def isHigher(card : CardInterface):Boolean
    def getSymbol:Symbol
    def getValue:Value
    def intValue:Int
