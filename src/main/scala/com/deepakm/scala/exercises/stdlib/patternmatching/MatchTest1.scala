package com.deepakm.scala.exercises.stdlib.patternmatching

object MatchTest1 extends App {
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many" // case _ will trigger if all other cases fail.
  }
  println(matchTest(3)) // prints "many"
}
