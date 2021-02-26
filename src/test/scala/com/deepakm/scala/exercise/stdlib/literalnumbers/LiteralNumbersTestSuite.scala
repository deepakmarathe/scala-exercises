package com.deepakm.scala.exercise.stdlib.literalnumbers

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

@RunWith(classOf[JUnitRunner])
class LiteralNumbersTestSuite extends AnyFunSuite{
  test("Integer literals are 32-bit and can be created from decimals as well as hexadecimals:"){
    val a = 2
    val b = 31
    val c = 0x30f
    val e = 0
    val f = -2
    val g = -31
    val h = -0x30f
    a should be(2)
    b should be(31)
    c should be(783) //Hint: 30F = 783
    e should be(0)
    f should be(-2)
    g should be(-31)
    h should be(-783)
  }

  test("Long literals are 64-bit. They are specified by appending an L at the end of the declaration: "){
    val a = 2L
    val b = 31L
    val c = 0x30fL
    val e = 0L
    val f = -2L
    val g = -31L
    val h = -0x30fL

    a should be(2L)
    b should be(31L)
    c should be(783L) //Hint: 30F = 783
    e should be(0L)
    f should be(-2L)
    g should be(-31L)
    h should be(-783L)
  }

  test("Float and Double literals conform to IEEE-754. Floats are 32-bit, while doubles are 64-bit. Floats can be defined using a f or F suffix, while doubles use a d or D suffix. Exponents are specified using e or E. "){
    val a = 3.0
    val b = 3.00
    val c = 2.73
    val d = 3f
    val e = 3.22d
    val f = 93e-9
    val g = 93e-9
    val h = 0.0
    val i = 9.23e-9d

    a should be(3.0)
    b should be(3.00)
    c should be(2.73)
    d should be(3f)
    e should be(3.22d)
    f should be(93e-9)
    g should be(93e-9)
    h should be(0.0)
    i should be(9.23e-9d)
  }
}
