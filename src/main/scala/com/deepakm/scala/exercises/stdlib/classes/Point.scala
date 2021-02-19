package com.deepakm.scala.exercises.stdlib.classes

class Point(val x: Int, val y: Int) {
  override def toString(): String = "(" + x + ", " + y + ")"
}

object Classes {
  def main(args: Array[String]): Unit = {
    val pt = new Point(1,2)
    println(pt)
  }
}