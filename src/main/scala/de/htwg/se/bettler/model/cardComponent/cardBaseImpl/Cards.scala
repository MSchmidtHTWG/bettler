package de.htwg.se.bettler
package model
package cardComponent
package cardBaseImpl

case class Cards(cards : Set[Card]):
    def returnSet = cards

    def contains(c : Cards) = !c.cards.isEmpty && (c.cards -- cards).size == 0

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

    def size = cards.size

    def groupBySameValue : Vector[Cards] =
        var group = Vector.empty[Cards]
        for (value <- 7 to 14)
            var groupCards = Set.empty[Card]
            for (card <- cards)
                if card.value.getValue.equals(value) then
                    groupCards += card
            if groupCards.nonEmpty then
                group = group :+ Cards(groupCards)
        return group

    /*def findLowest : Cards =
        val cardList = cards.toList
        var i = 0
        for (card <- cardList)
            if card.value.getValue < cardList(i).value.getValue then
                i += 1
        return Cards(Set(cardList(i)))*/

    def findPlayable(board : Cards) : Option[Cards] =
        for (c <- this.groupBySameValue)
            if board.isWorse(c) then return Some(c)
        for (c <- this.groupBySameValue)
            if c.size > board.size then
                var reducedCards = Set.empty[Card]
                c.returnSet.toArray.slice(0, board.size).foreach{x => reducedCards = reducedCards + x}
                if board.isWorse(Cards(reducedCards)) then return Some(Cards(reducedCards))
        return None