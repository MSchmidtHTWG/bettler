package scala

class Handler {
    private val deck = new Deck();
    private var spieler1 = deck.draw();
    private var spieler2 = deck.draw();
    private var spielfeld : List[(Char, String)] = Nil;
    private var gamestate = "Start";
    def handle(input : String) : Unit = {
        input match {
            case "Start" => {
                field.printField(2, spieler1, spieler2);
            }
            case _ => {
            } 
        }
    }
}

