package com.deepakm.scala.exercise.stdlib.literalstrings

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

@RunWith(classOf[JUnitRunner])
class LiteralStringSuite extends AnyFunSuite{
  test("Character literals are quoted with single quotes: "){
    val a = 'a'
    val b = 'B'

    a.toString should be("a")
    b.toString should be("B")
  }

  test("Character literals can use hexadecimal Unicode: "){
    val c = 'a' //unicode for a

    c.toString should be("a")
  }

  test("Character literals can use escape sequences: "){
    val e = '\"'
    val f = '\\'

    e.toString should be("\"")
    f.toString should be("\\")
  }

  test("One-line String literals are surrounded by quotation marks: "){
    val a = "To be or not to be"
    a should be("To be or not to be")
  }
}
