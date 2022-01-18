package de.htwg.se.bettler
package model
package cardComponent
package cardBaseImpl

import model.cardComponent.cardBaseImpl.Card

case class Cards(cards : Set[CardInterface]) extends CardsInterface:
    override def toString =
        var string = ""
        cards.foreach(c => string += c.toString)
        string

    def returnSet = cards

    def add(card : CardInterface) = Cards(cards + card)
    def add(cards : CardsInterface) = Cards(this.cards ++ cards.returnSet)
    def remove(card : CardInterface) = Cards(cards - card)
    def remove(cards : CardsInterface) = Cards(this.cards -- cards.returnSet)

    def contains(c : CardsInterface) = !c.returnSet.isEmpty && (c.returnSet -- cards).size == 0

    def isWorse(c: CardsInterface) : Boolean =
        if c.returnSet.isEmpty then return false
        if cards.size == 0 then return true
        if c.returnSet.size != cards.size then return false
        for {
            c1 <- cards
            c2 <- c.returnSet
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

    def size = cards.size

    def groupBySameValue : Vector[CardsInterface] =
        var group = Vector.empty[Cards]
        for (value <- 7 to 14)
            var groupCards = Set.empty[CardInterface]
            for (card <- cards)
                if card.intValue.equals(value) then
                    groupCards += card
            if groupCards.nonEmpty then
                group = group :+ Cards(groupCards)
        return group


    def findPlayable(board : CardsInterface) : Option[CardsInterface] =
        for (c <- this.groupBySameValue)
            if board.isWorse(c) then return Some(c)
        for (c <- this.groupBySameValue)
            if c.size > board.size then
                var reducedCards = Set.empty[CardInterface]
                c.returnSet.toArray.slice(0, board.size).foreach{x => reducedCards = reducedCards + x}
                if board.isWorse(Cards(reducedCards)) then return Some(Cards(reducedCards))
        return None

    def bestCards : CardsInterface = 
        var tmp = cards.head
        for (card <- cards)
            if card.isHigher(tmp) then
                tmp = card
        return Cards(Set(tmp))

    def worstCards : CardsInterface = 
        var tmp = cards.head
        for (card <- cards)
            if tmp.isHigher(card) then
                tmp = card
        return Cards(Set(tmp))
