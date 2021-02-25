package com.deepakm.scala.exercises.stdlib.namedanddefaultarguments

object NamedAndDefaultArguments extends App {
  def printName(first: String, last: String) = {
    println(first + " " + last)
  }

  printName("John", "Smith") // Prints "John Smith"
  printName(first = "John", last = "Smith") // Prints "John Smith"
  printName(last = "Smith", first = "John") // Prints "John Smith"

  def printName2(first: String = "John", last: String = "Smith") = {
    println(first + " " + last)
  }
  printName2(last = "Jones") // Prints "John Jones"


}

