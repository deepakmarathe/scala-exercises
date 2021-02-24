package com.deepakm.scala.exercise.stdlib.lambdas

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LambdasSuite extends AnyFunSuite {
  test("lambda demo 1") {
    def lambda = { x: Int => x + 1 }

    val result = lambda(3)
    result should be(4)

    val result1andhalf = lambda.apply(3)
    result1andhalf should be(4)

    def lambda2 = (x: Int) => x + 2

    lambda2(3) should be(5)

    def lambda3 = (x: Int) => x + 3

    lambda3(3) should be(6)

    def lambda4 = new Function[Int, Int] {
      def apply(v1: Int): Int = v1 - 1
    }

    lambda4(3) should be(2)

    def lambda5(x: Int) = x + 1

    lambda5(3) should be(4)
  }

  test("anonymous function take can a different look by taking out brackets") {
    def lambda = (x: Int) => x + 1

    lambda(5) should be(6)
  }

  test("bit about closures") {
    var incrementer = 1

    def closure = { x: Int => x + incrementer }

    val result1 = closure(10)
    result1 should be(11)

    incrementer = 2
    val result2 = closure(10)
    result2 should be(12)
  }

  test("higher order functions") {
    def summation(x: Int, y: Int => Int) = y(x)

    var incrementer = 3

    def closure = (x: Int) => x + incrementer

    val result = summation(10, closure)
    result should be(13)
  }

  test("Higher order function returning another function") {
    def addWithoutSyntaxSugar(x: Int): Function1[Int, Int] = {
      new Function1[Int, Int]() {
        def apply(y: Int): Int = x + y
      }
    }

    addWithoutSyntaxSugar(1).isInstanceOf[Function1[Int, Int]] should be(true)
    addWithoutSyntaxSugar(2)(3) should be(5)

    def fiveAdder: Function1[Int, Int] = addWithoutSyntaxSugar(5)

    fiveAdder(5) should be(10)
  }

  test("Function returning another function using an anonymous function") {
    def addWithSyntaxSugar(x: Int) = (y: Int) => x + y

    addWithSyntaxSugar(1).isInstanceOf[Function1[Int, Int]] should be(true)
    addWithSyntaxSugar(2)(3) should be(5)

    def fiveAdder = addWithSyntaxSugar(5)

    fiveAdder(5) should be(10)
  }

  test("isInstanceOf is agnostic of types at runtime") {
    def addWithSyntaxSugar(x: Int) = (y: Int) => x + y

    addWithSyntaxSugar(1).isInstanceOf[Function1[_, _]] should be(true)
  }

  test("function taking another function as parameter. helps in composing functions.") {
    def makeUpper(xs: List[String]) = xs map {
      _.toUpperCase
    }

    def makeWhatEverYouLike(xs: List[String], sideEffect: String => String) = xs map sideEffect

    makeUpper(List("abc", "xyz", "123")) should be(List("ABC", "XYZ", "123"))

    makeWhatEverYouLike(List("ABC", "XYZ", "123"), x => x.toLowerCase) should be(List("abc", "xyz", "123"))

    val myName = (name:String) => s"My name is $name"
    makeWhatEverYouLike(List("John", "Mark"), myName) should be(List("My name is John", "My name is Mark"))

    List("Scala", "Erlang", "Clojure") map (_.length) should be(List(5, 6, 7))
  }
}
