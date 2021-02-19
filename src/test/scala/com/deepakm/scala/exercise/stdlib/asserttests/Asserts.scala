package com.deepakm.scala.exercise.stdlib.asserttests

import com.deepakm.scala.exercises.stdlib.asserttests.AdditionCommand
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Asserts extends AnyFunSuite {
  val additionCommand = new AdditionCommand(1, 1)

  test("assert") {
    val left = 1
    val right = 1
    assert(left == right)
    assert(left + right == additionCommand.add(left, right))
  }

  test("should equal") {
    val left = 1
    val right = 1
    // can customize equality
    left should equal(right)
    left + right should equal(additionCommand.add(left, right))
  }

  test("should ===") {
    val left = 1
    val right = 1
    // can customize equality and enforce type constraints
    left should ===(right)
    left + right should ===(additionCommand.add(left, right))
  }

  //  test("should be") {
  //    val left = 1
  //    val right = 1
  //    // cannot customize equality, so fastest to compile
  //    left should be(right)
  //    true should be(true)
  //  }

  test("shouldEqual") {
    val left = 1
    val right = 1
    // can customize equality, no parentheses required
    left shouldEqual right
    left + right shouldEqual additionCommand.add(left, right)
  }

  test("shouldBe") {
    val left = 1
    val right = 1
    // cannot customize equality, so fastest to compile, no parentheses required
    left shouldBe right
    left + right shouldBe additionCommand.add(left, right)
  }
}
