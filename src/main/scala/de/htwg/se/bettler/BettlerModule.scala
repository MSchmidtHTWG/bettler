package de.htwg.se.bettler

import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import com.google.inject.name.Names
import com.google.inject.Binder
import net.codingwell.scalaguice.ScalaModule
import controller._
import controller.controllerBaseImp.Controller
import model.gameComponent._
import model.gameComponent.pvpGameImpl._
import model.cardComponent._
import model.cardComponent.cardBaseImpl._
import java.util.ResourceBundle.Control
import model.fileIOComponent.FileIOInterface
import model.fileIOComponent._

class BettlerModule extends AbstractModule {
    override def configure() = {
        bind(classOf[ControllerInterface]).toInstance(Controller(None))
        //bind(classOf[FileIOInterface]).toInstance(fileIOJSon.FileIO())
    }
}