package de.htwg.se.bettler
package model

case class Cards(cards : Set[Card]):
    def returnSet = cards
    def contains(c : Set[Card]) = c.isEmpty || (c -- cards).isEmpty
    def isWorse(c: Set[Card]) : Boolean = {
        if c.isEmpty then return false
        if c.size != cards.size then return false
        for {
            c1 <- cards
            c2 <- c
            if !c2.isHigher(c1)
        } return false
        return true
    }
    def isPlayable : Boolean = {
        if cards.isEmpty then return false
        for {
            c1 <- cards
            c2 <- cards
            if !c1.sameValue(c2)
        } return false
        return true
    }

