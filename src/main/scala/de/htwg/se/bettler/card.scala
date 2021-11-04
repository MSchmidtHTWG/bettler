package scala

object card {
    def isCard(card : Tuple2[Char, String]) : Boolean = {
        return true;
    }
    
    def returnValueInt(value : String) : Int = {
        value match {
            case "7" => return 0
            case "8" => return 1
            case "9" => return 2
            case "10" => return 3
            case "Jack" => return 4
            case "Queen" => return 5
            case "King" => return 6
            case "Ace" => return 7 
            case _ => return -1
        }
    }

    def isHigher(value1 : String, value2 : String) : Boolean = {
        return card.returnValueInt(value1) > card.returnValueInt(value2);
    }
}