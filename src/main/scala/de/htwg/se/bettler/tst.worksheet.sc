import de.htwg.se.bettler._
import model._

val deck = Deck(32)
var r = deck.draw()

val game = Game()
val state = P1TurnState()
val cards = game.spieler1.returnSet.head
val game2 = state.play(Set.empty[Card], game)
val bool = game2.spieler1.equals(game.spieler1)