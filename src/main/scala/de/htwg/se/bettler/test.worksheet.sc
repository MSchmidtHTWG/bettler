1+3
var x : List[Int] = 1::2::3::Nil
x
x = x.filterNot(_ == 2)
x
var y = System.currentTimeMillis()