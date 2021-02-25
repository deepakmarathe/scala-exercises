package com.deepakm.scala.exercises.stdlib.extractors

object Twice {
  def apply(x: Int): Int = x * 2
  def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some(z / 2) else None
}

object TwiceTest extends App {
  val x = Twice(21)
  x match { case Twice(n) => Console.println(n) } // prints 21

  val y = Twice.apply(21)
  Twice.unapply(y) match { case Some(n) => Console.println(n) } // prints 21
}