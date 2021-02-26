package com.deepakm.scala.exercise.stdlib.literalbooleans

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

@RunWith(classOf[JUnitRunner])
class LiteralBooleansSuite extends AnyFunSuite{
  test("Boolean literals are either true or false, using the true or false keyword: "){
    val a = true
    val b = false
    val c = 1 > 2
    val d = 1 < 2
    val e = a == c
    val f = b == d
    a should be(true)
    b should be(false)
    c should be(false)
    d should be(true)
    e should be(false)
    f should be(false)
  }
}
