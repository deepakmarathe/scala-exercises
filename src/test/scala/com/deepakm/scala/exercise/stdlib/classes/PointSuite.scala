package com.deepakm.scala.exercise.stdlib.classes

import com.deepakm.scala.exercises.stdlib.asserttests.AdditionCommand
import com.deepakm.scala.exercises.stdlib.classes.Point
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.must.Matchers.be

@RunWith(classOf[JUnitRunner])
class PointSuite extends AnyFunSuite {
  val left = 1;
  val right = 1
  val point = new Point(left, right)
  val additionCommand = new AdditionCommand(point.x, point.y)

  test("assert") {
    assert(left == right)
    assert(left + right == additionCommand.add(left, right))
  }

  test("should equal") {
    // can customize equality
    left should equal(right)
    left + right should equal(additionCommand.add(left, right))
  }

  test("should ===") {
    // can customize equality and enforce type constraints
    left should ===(right)
    left + right should ===(additionCommand.add(left, right))
  }

    test("should be") {
      val left = 1
      val right = 1
      // cannot customize equality, so fastest to compile
      left should be(right)
      true should be(true)
    }

  test("shouldEqual") {
    // can customize equality, no parentheses required
    left shouldEqual right
    left + right shouldEqual additionCommand.add(left, right)
  }

  test("shouldBe") {
    // cannot customize equality, so fastest to compile, no parentheses required
    left shouldBe right
    left + right shouldBe additionCommand.add(left, right)
  }
}
