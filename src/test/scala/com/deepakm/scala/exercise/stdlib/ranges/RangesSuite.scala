package com.deepakm.scala.exercise.stdlib.ranges

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RangesSuite extends AnyFunSuite {
  test("Ranges upper bound is not inclusive") {
    val someNumbers = Range(0, 10)
    val second = someNumbers(1)
    val last = someNumbers.last

    someNumbers.size should be(10)
    second should be(1)
    last should be(9)
  }

  test("Ranges can be split using until") {
    val someNumbers = Range(0, 10)
    val otherRange = 0 until 10
    (someNumbers == otherRange) should be(true)
  }

  test("Ranges can specify a step for increment"){
    val someNumbers = Range(2, 10, 3)
    val second = someNumbers(1)
    val last = someNumbers.last

    someNumbers.size should be(3)
    second should be(5)
    last should be(8)
  }

  test("A range does not include its upper bound, even in a step increment: "){
    val someNumbers = Range(0, 34, 2)
    someNumbers.contains(33) should be(false)
    someNumbers.contains(32) should be(true)
    someNumbers.contains(34) should be(false)
  }

  test("Range can specify to include its upper bound value: "){
    val someNumbers = Range(0, 34).inclusive

    someNumbers.contains(34) should be(true)
  }

  test("Inclusive ranges can be specified using 'to': "){
    val someNumbers = Range(0, 34).inclusive
    val otherRange = 0 to 34

    (someNumbers == otherRange) should be(true)
  }

}
