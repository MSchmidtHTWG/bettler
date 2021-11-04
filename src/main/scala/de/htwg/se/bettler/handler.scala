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
                field.printField(spielfeld, spieler1, spieler2);
                this.gamestate = "Done";
            }
            case "Exit" => {
                println("Handler hat input Exit bekommen und gamestate auf Exit gesetzt")
                this.gamestate = "Exit"
            }
            case _ => {
            } 
        }
    }
    def getGamestate() : String = this.gamestate;
}

