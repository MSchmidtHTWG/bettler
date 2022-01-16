package de.htwg.se.bettler

import com.google.inject.Guice
import de.htwg.se.bettler.controller._
import de.htwg.se.bettler.controller.controllerBaseImp._
import model.gameComponent.Game
import aview.TUI
import aview.gui._

@main def Main: Unit =
  val injector = Guice.createInjector(new BettlerModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = TUI(controller)
  //val gui = SwingGui(controller)
  tui.run
