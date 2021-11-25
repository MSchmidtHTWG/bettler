package de.htwg.se.bettler
package model

case class Cards(cards : Set[Card]):
    def returnSet = cards
    def contains(c : Cards) = c.cards.isEmpty || (c.cards -- cards).size == 0
    def isWorse(c: Cards) : Boolean =
        if c.cards.isEmpty then return false
        if cards.size == 0 then return true
        if c.cards.size != cards.size then return false
        for {
            c1 <- cards
            c2 <- c.cards
            if !c2.isHigher(c1)
        } return false
        return true
    def isPlayable : Boolean =
        if cards.isEmpty then return false
        for {
            c1 <- cards
            c2 <- cards
            if !c1.sameValue(c2)
        } return false
        return true
    def remove(c : Cards) = Cards(this.cards -- c.cards)

