package com.deepakm.scala.exercise.stdlib.formatting

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FormattingSuite extends AnyFunSuite {
  test("String can be placed in format: ") {
    val s = "Hello World"
    "Application %s".format(s) should be("Application Hello World")
  }

  test("Character Literals can be a single character: ") {
    val a = 'a'
    val b = 'B'

    //format(a) is a string format, meaning the "%c".format(x)
    //will return the string representation of the char.

    "%c".format(a) should be("a")
    "%c".format(b) should be("B")
  }

  test("Character Literals can be an escape sequence, including hexidecimal: "){
    val c = 'a' //unicode for a
    val e = '\"'
    val f = '\\'

    "%c".format(c) should be("a")
    "%c".format(e) should be("\"")
    "%c".format(f) should be("\\")
  }

  test("Formatting can also include numbers: "){
    val j = 190
    "%d bottles of beer on the wall" format j - 100 should be("90 bottles of beer on the wall")
  }

  test("Formatting can be used for any number of items, like a string and a number:"){
    val j = 190
    val k = "vodka"

    "%d bottles of %s on the wall".format(j - 100, k) should be("90 bottles of vodka on the wall")
  }
}
