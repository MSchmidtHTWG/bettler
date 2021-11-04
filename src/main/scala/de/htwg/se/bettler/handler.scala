package scala

class Handler {
    private val deck = new Deck();
    private var temp : List[(Char, String)] = Nil;
    private var spieler1 = deck.draw();
    private var spieler2 = deck.draw();
    private var spielfeld : List[(Char, String)] = Nil;
    private var gamestate = "start";
    def handle(input : String) : Unit = {
        if input == "exit" then {
            this.gamestate = "exit";
            return;
        }
        gamestate match {
            case "start" => {
                if input == "start" then {
                    field.printField(spielfeld, spieler1, spieler2);
                    this.gamestate = "player1";
                    println("Eine neue Runde hat begonnen. Spieler 1 beginnt. Tippe 'spiele ' gefolgt von Karten (z.B.: spiele H,Ace C,Ace)");
                    println("Das Spiel kann jederzeit mit 'exit' beendet werden.")
                } else {
                    println("Falsche Eingabe!");
                }
            }
            case "player1" => {
                var s = input.split(" ");
                if s(0) != "spiele" then {
                    println("Falsche Eingabe!");
                    return;
                }
                if s.length < 2 || s.length > 5 then {
                    println("Du musst 1-4 Karten spielen.");
                    return;
                }
                s = s.filterNot(_ == "spiele");
                for (i <- s) {
                    var c = i.split(",");
                    if card.isCard((c(0).charAt(0)), c(1)) == false then {
                        println("Falsche Eingabe! " + c + " stellt keine Karte dar.");
                        temp = Nil;
                        return;
                    }
                    temp = (c(0).charAt(0), c(1)) :: temp;
                    println(temp);
                }
                if spieler1.contains(temp) == false then {
                    println("Spieler 1 hat diese Karten nicht auf der Hand.")
                    temp = Nil;
                    return;
                }
                var (symbol, value) = temp(0);
                if temp.length > 2 then {
                    for (j <- 1 to temp.length) {
                        var  (symbol2, value2) = temp(j);
                        if value != value2 then {
                            println("Die Karten müssen den gleichen Wert haben!");
                            temp = Nil;
                            return;
                        }
                    }
                }
                if spielfeld == Nil then {
                    spielfeld = temp;
                } else if spielfeld.length != temp.length then {
                    println("Du musst genau so viele Karten spielen, wie auf dem Spielfeld liegen!");
                    temp = Nil;
                    return;
                } else {
                    var (symbol3, value3) = spielfeld(0);
                    if card.isHigher(value, value3) == false then {
                        println("Der Wert der gespielten Karten muss größer sein als der Wert der Karten auf dem Spielfeld!");
                        temp = Nil;
                        return;
                    }
                    spielfeld = temp;
                    for (k <- 0 to temp.length) {
                        spieler1 = spieler1.filterNot(_ == temp(k));
                    }
                    field.printField(spielfeld, spieler1, spieler2);
                    this.gamestate = "player2";
                    if spieler1.length == 0 then {
                        this.gamestate = "done";
                        println("Spieler 1 hat gewonnen.")
                    }
                    temp = Nil;
                }
            }
            case "player2" => {
                var s = input.split(" ");
                if s(0) != "spiele" then {
                    println("Falsche Eingabe!");
                    return;
                }
                if s.length < 2 || s.length > 5 then {
                    s = s.slice(1, s.length);
                }

                for (i <- 0 to s.length) {
                    var c = s(i).split(",");
                    if card.isCard((c(0).charAt(0)), c(1)) == false then {
                        println("Falsche Eingabe! " + c + " stellt keine Karte dar.");
                        temp = Nil;
                        return;
                    }
                    temp = (c(0).charAt(0), c(1)) :: temp;
                }
                if spieler2.contains(temp) == false then {
                    println("Spieler 2 hat diese Karten nicht auf der Hand.")
                    temp = Nil;
                    return;
                }
                var (symbol, value) = temp(0);
                if temp.length > 2 then {
                    for (j <- 1 to temp.length) {
                        var  (symbol2, value2) = temp(j);
                        if value != value2 then {
                            println("Die Karten müssen den gleichen Wert haben!");
                            temp = Nil;
                            return;
                        }
                    }
                }
                if spielfeld == Nil then {
                    spielfeld = temp;
                } else if spielfeld.length != temp.length then {
                    println("Du musst genau so viele Karten spielen, wie auf dem Spielfeld liegen!");
                    temp = Nil;
                    return;
                } else {
                    var (symbol3, value3) = spielfeld(0);
                    if card.isHigher(value, value3) == false then {
                        println("Der Wert der gespielten Karten muss größer sein als der Wert der Karten auf dem Spielfeld!");
                        temp = Nil;
                        return;
                    }
                    spielfeld = temp;
                    for (k <- 0 to temp.length) {
                        spieler2 = spieler2.filterNot(_ == temp(k));
                    }
                    field.printField(spielfeld, spieler1, spieler2);
                    this.gamestate = "player1";
                    if spieler1.length == 0 then {
                        this.gamestate = "done";
                        println("Spieler 2 hat gewonnen.")
                    }
                    temp = Nil;
                }
            }
        }
    }
    def getGamestate() : String = this.gamestate;
}

