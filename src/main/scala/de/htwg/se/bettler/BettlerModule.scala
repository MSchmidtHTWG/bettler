package de.htwg.se.bettler

import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import com.google.inject.name.Names
import com.google.inject.Binder
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.bettler.controller._
import de.htwg.se.bettler.controller.controllerBaseImp.Controller
import de.htwg.se.bettler.model.gameComponent._
import de.htwg.se.bettler.model.gameComponent.pvpGameImpl._
import de.htwg.se.bettler.model.cardComponent._
import de.htwg.se.bettler.model.cardComponent.cardBaseImpl._
import java.util.ResourceBundle.Control
import de.htwg.se.bettler.model.fileIOComponent.FileIOInterface
import de.htwg.se.bettler.model.fileIOComponent._

class BettlerModule extends AbstractModule {
    override def configure() = {
        bind(classOf[ControllerInterface]).toInstance(Controller(None))
        bind(classOf[FileIOInterface]).toInstance(fileIOJSonImpl.FileIOJson())
    }
}