package scala

import scala.util.Random

class Deck {
    var cards = ('H', "7") :: ('H', "8") :: ('H', "9") :: ('H', "10") :: ('H', "Jack") :: ('H', "Queen") :: ('H', "King") :: ('H', "Ace")
        :: ('S', "7") :: ('S', "8") :: ('S', "9") :: ('S', "10") :: ('S', "Jack") :: ('S', "Queen") :: ('S', "King") :: ('S', "Ace")
        :: ('D', "7") :: ('D', "8") :: ('D', "9") :: ('D', "10") :: ('D', "Jack") :: ('D', "Queen") :: ('D', "King") :: ('D', "Ace")
        :: ('C', "7") :: ('C', "8") :: ('C', "9") :: ('C', "10") :: ('C', "Jack") :: ('C', "Queen") :: ('C', "King") :: ('C', "Ace") :: Nil

    def draw() : List[(Char, String)] = {
        val r : List[(Char, String)] = cards.slice(0, 7);
        cards = cards.slice(7,cards.length);
        return r;
    }
    def shuffle() : Unit = {
        val r = scala.util.Random();
        r.setSeed(System.currentTimeMillis());
        this.cards = r.shuffle(this.cards);
    }
}
