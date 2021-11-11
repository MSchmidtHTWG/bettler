package scala

class Handler {
    private val deck = new Deck();
    deck.shuffle();
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
                    println(field.printField(spielfeld, spieler1, spieler2));
                    this.gamestate = "player1";
                    println("Eine neue Runde hat begonnen. Spieler 1 beginnt. Tippe 'spiele ' gefolgt von Karten (z.B.: spiele H,Ace C,Ace)");
                    println("Das Spiel kann jederzeit mit 'exit' beendet werden.")
                } else {
                    println("Falsche Eingabe!");
                }
            }
            case "player1" => {
                temp = Nil;
                if input == "skip" then {
                    this.gamestate = "player2";
                    println("Spieler 1 möchte oder kann nicht legen. Spieler 2 ist an der Reihe.");
                    spielfeld = temp;
                    return;
                }
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
                        return;
                    }
                    temp = (c(0).charAt(0), c(1)) :: temp;
                }
                for (t <- temp) {
                    if spieler1.contains(t) == false then {
                        println("Spieler 1 hat diese Karten nicht auf der Hand.")
                        return;
                    } 
                }
                var (symbol, value) = temp(0);
                if temp.length > 2 then {
                    for (j <- 1 to temp.length - 1) {
                        var  (symbol2, value2) = temp(j);
                        if value != value2 then {
                            println("Die Karten müssen den gleichen Wert haben!");
                            return;
                        }
                    }
                }
                if spielfeld == Nil then {
                    spielfeld = temp;
                } else if spielfeld.length != temp.length then {
                    println("Du musst genau so viele Karten spielen, wie auf dem Spielfeld liegen!");
                    return;
                } else {
                    var (symbol3, value3) = spielfeld(0);
                    if card.isHigher(value, value3) == false then {
                        println("Der Wert der gespielten Karten muss größer sein als der Wert der Karten auf dem Spielfeld!");
                        return;
                    }
                }
                spielfeld = temp;
                for (k <- temp) {
                    spieler1 = spieler1.filterNot(_ == k);
                }
                println(field.printField(spielfeld, spieler1, spieler2));
                this.gamestate = "player2";
                if spieler1.length == 0 then {
                    this.gamestate = "done";
                    println("Spieler 1 hat gewonnen.")
                    return;
                }
                println("Spieler 2 ist an der Reihe.")
            }
            case "player2" => {
                temp = Nil;
                if input == "skip" then {
                    this.gamestate = "player1";
                    println("Spieler 2 möchte oder kann nicht legen. Spieler 1 ist an der Reihe.");
                    spielfeld = temp;
                    return;
                }
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
                        return;
                    }
                    temp = (c(0).charAt(0), c(1)) :: temp;
                }
                for (t <- temp) {
                    if spieler2.contains(t) == false then {
                        println("Spieler 2 hat diese Karten nicht auf der Hand.")
                        return;
                    } 
                }
                var (symbol, value) = temp(0);
                if temp.length > 1 then {
                    for (j <- 1 to temp.length - 1) {
                        var  (symbol2, value2) = temp(j);
                        if value != value2 then {
                            println("Die Karten müssen den gleichen Wert haben!");
                            return;
                        }
                    }
                }
                if spielfeld == Nil then {
                    spielfeld = temp;
                } else if spielfeld.length != temp.length then {
                    println("Du musst genau so viele Karten spielen, wie auf dem Spielfeld liegen!");
                    return;
                } else {
                    var (symbol3, value3) = spielfeld(0);
                    if card.isHigher(value, value3) == false then {
                        println("Der Wert der gespielten Karten muss größer sein als der Wert der Karten auf dem Spielfeld!");
                        return;
                    }
                }
                spielfeld = temp;
                for (k <- temp) {
                    spieler2 = spieler2.filterNot(_ == k);
                }
                println(field.printField(spielfeld, spieler1, spieler2));
                this.gamestate = "player1";
                if spieler2.length == 0 then {
                    this.gamestate = "done";
                    println("Spieler 2 hat gewonnen.");
                    return;
                }
                println("Spieler 1 ist an der Reihe.");
            }
            case "done" => {
                if input = "new" then {
                    
                }
            }
        }
    }
    def getGamestate() : String = this.gamestate;

    def playedIsCard(cards : Array[String]) : Boolean = {
        for (c <- cards) {
            if c.length < 3 || c(1) != ',' then {
                return false
            }
            var s = c.split(",");
            if s.length != 2 then {return false;}
            if s(0).length != 1 then {return false;}
            if !card.isCard(s(0).charAt(0), s(1)) then {return false;}
        }
        return true;
    }
}

