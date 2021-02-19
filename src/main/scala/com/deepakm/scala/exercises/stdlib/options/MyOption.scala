package com.deepakm.scala.exercises.stdlib.options

class MyOption {
  def maybeItWillReturnSomething(flag: Boolean): Option[String] = {
    if (flag) Some("Found value") else None
  }
}
