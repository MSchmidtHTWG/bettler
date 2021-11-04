1+3
var x : List[Int] = 1::2::3::Nil
x
x = x.filterNot(_ == 2)
x
var y = System.currentTimeMillis()

var cards = ('H', "7") :: ('H', "8") :: ('H', "9") :: ('H', "10") :: ('H', "Jack") :: ('H', "Queen") :: ('H', "King") :: ('H', "Ace")
        :: ('S', "7") :: ('S', "8") :: ('S', "9") :: ('S', "10") :: ('S', "Jack") :: ('S', "Queen") :: ('S', "King") :: ('S', "Ace")
        :: ('D', "7") :: ('D', "8") :: ('D', "9") :: ('D', "10") :: ('D', "Jack") :: ('D', "Queen") :: ('D', "King") :: ('D', "Ace")
        :: ('C', "7") :: ('C', "8") :: ('C', "9") :: ('C', "10") :: ('C', "Jack") :: ('C', "Queen") :: ('C', "King") :: ('C', "Ace") :: Nil
print(cards)
print(cards(0))
print(cards.foreach(t => print(t)))
var z = cards.foreach(t => t.toString())
z
var t : String = ""
var v = cards.foreach{t+=_}
t
v