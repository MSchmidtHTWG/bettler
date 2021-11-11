package de.htwg.se.bettler
package model


class P2TurnState(game : Game) extends State:
    var msg = "Spieler 2 ist an der Reihe."
    override def toString = msg
    override def play(cards : Set[Card]) : Unit =
        if cards.isEmpty then {
            msg = "Falsche Eingabe. Spiele Karten mit 'play Karte1 Karte2 ..'. Spieler 2 ist an der Reihe."
            return
        }
        if !Card.isPartOfSet(cards, game.spieler2) then {
            msg = "Spieler 2 hat diese Karten nicht."
            return
        }
        if !Card.isPlayAble(cards) then {
            msg = "Die Karten haben nicht den gleichen Wert."
            return
        }
        if !Card.isBetter(cards, game.spielfeld) then {
            msg = "Es müssen genau gleichviele Karten gespielt wie auf dem Spielfeld liegen und ihr Wert muss größer sein."
            return
        }
        game.spieler2 = game.spieler2 -- cards
        game.spielfeld = cards
        game.state = P1TurnState(game)
    
    override def handle(input : String) : Unit =
        var s = input.split(" ")
        if (s.length < 1 || s.length > 5) {
            msg = "Falsche Eingabe. Spiele Karten mit 'play Karte1 Karte2 ..'. Spieler 2 ist an der Reihe."
            return
        }
        if (s(0) == "play") {
            var cset = Set.empty[Card]
            var c : Card = null
            for (i <- 1 to s.length - 1) {
                c = Card.returnCard(s(i))
                if c == null then {
                    msg = "Nicht existente Karte eingegeben. Spieler 2 ist an der Reihe"
                    return
                }
                cset = cset + c
            }

            if !(cset subsetOf game.spieler2) then {
                msg = "Spieler 2 hat diese Karten nicht auf der Hand. Spieler 2 ist an der Reihe."
                return
            }

            for (i <- cset) {
                if !i.sameValue(c) then {
                    msg = "Es dürfen nur Karten vom gleichen Wert gespielt werden. Spieler 1 ist an der Reihe."
                    return
                }
            }

            if game.spielfeld.isEmpty then {
                game.spielfeld = cset
                game.spieler2 = game.spieler2 -- cset
                game.state = P1TurnState(game)
                return
            } else if game.spielfeld.size != cset.size then {
                msg = "Es müssen genau so viele Karten gespielt werden, wie auf dem Feld liegen"
                return
            } else if !cset.head.isHigher(game.spielfeld.head) then {
                msg = "Die Karten müssen einen höheren Wert haben als die Karten auf dem Spielfeld. Spieler 2 ist an der Reihe."
                return
            } else {
                game.spielfeld = cset
                game.spieler2 = game.spieler2 -- cset
                game.state = P1TurnState(game)
                return
            }
        }