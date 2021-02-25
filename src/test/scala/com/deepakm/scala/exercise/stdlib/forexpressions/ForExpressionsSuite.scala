package com.deepakm.scala.exercise.stdlib.forexpressions

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.must.Matchers.be

@RunWith(classOf[JUnitRunner])
class ForExpressionsSuite extends AnyFunSuite {
  test("for expressions can nest") {
    val xValues = 1 to 4
    val yValues = 1 to 2
    val coordinates = for {
      x <- xValues
      y <- yValues
    } yield (x, y)
    coordinates(4) should be (3,1)
  }

  test("Using for we can make more readable code: "){
    val nums = List(List(1), List(2), List(3), List(4), List(5))

    val result = for {
      numList <- nums
      num <- numList
      if (num % 2 == 0)
    } yield (num)

    result should be(List(2,4))

    // Which is the same as
    nums.flatMap(numList => numList).filter(_ % 2 == 0) should be(result)

    // or the same as
    nums.flatten.filter(_ % 2 == 0) should be(result)
  }
}
