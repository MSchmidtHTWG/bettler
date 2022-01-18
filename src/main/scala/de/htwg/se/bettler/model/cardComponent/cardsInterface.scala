package de.htwg.se.bettler
package model
package cardComponent

trait CardsInterface:
    def returnSet:Set[CardInterface]
    def contains(c : CardsInterface):Boolean
    def isWorse(c: CardsInterface):Boolean
    def isPlayable : Boolean
    def remove(c : CardsInterface):CardsInterface
    def add(c : CardsInterface):CardsInterface
    def remove(c : CardInterface):CardsInterface
    def add(c : CardInterface):CardsInterface
    def size:Int
    def groupBySameValue : Vector[CardsInterface]
    def findPlayable(board : CardsInterface) : Option[CardsInterface]
    def bestCards : CardsInterface
    def worstCards : CardsInterface

//abstract class ACards extends CardsInterface