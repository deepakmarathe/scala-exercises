package com.deepakm.scala.exercise.stdlib.partialfunctions

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PartialFunctionsSuite extends AnyFunSuite {
  test("Partial Function Chaining") {
    val doubleEvens: PartialFunction[Int, Int] =
      new PartialFunction[Int, Int] {
        //States that this partial function will take on the task
        def isDefinedAt(x: Int) = x % 2 == 0

        //What we do if this partial function matches
        def apply(v1: Int) = v1 * 2
      }

    val tripleOdds: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
      def isDefinedAt(x: Int) = x % 2 != 0

      def apply(v1: Int) = v1 * 3
    }

    val whatToDo = doubleEvens orElse tripleOdds //Here we chain the partial functions together

    whatToDo(3) should be(9)
    whatToDo(4) should be(8)
  }

  test("case statements are quick way to create partial functions"){
    val doubleEvens: PartialFunction[Int, Int] = {
      case x if (x % 2) == 0 => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x if (x % 2) != 0 => x * 3
    }

    val whatToDo = doubleEvens orElse tripleOdds //Here we chain the partial functions together
    whatToDo(3) should be(9)
    whatToDo(4) should be(8)
  }

  test("The result of partial functions can have an andThen function added to the end of the chain: "){
    val doubleEvens: PartialFunction[Int, Int] = {
      case x if (x % 2) == 0 => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x if (x % 2) != 0 => x * 3
    }

    val addFive = (x: Int) => x + 5
    val whatToDo =
      doubleEvens orElse tripleOdds andThen addFive //Here we chain the partial functions together
    whatToDo(3) should be(14)
    whatToDo(4) should be(13)
  }

  test("andThen can be used to continue onto another chain of logic: "){
    val doubleEvens: PartialFunction[Int, Int] = {
      case x if (x % 2) == 0 => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x if (x % 2) != 0 => x * 3
    }

    val printEven: PartialFunction[Int, String] = {
      case x if (x % 2) == 0 => "Even"
    }
    val printOdd: PartialFunction[Int, String] = {
      case x if (x % 2) != 0 => "Odd"
    }

    val whatToDo = doubleEvens orElse tripleOdds andThen (printEven orElse printOdd)

    whatToDo(3) should be("Odd")
    whatToDo(4) should be("Even")
  }
}
