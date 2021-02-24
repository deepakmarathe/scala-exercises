package com.deepakm.scala.exercise.stdlib.lists

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import com.deepakm.scala.exercises.stdlib.asserttests.AdditionCommand
import com.deepakm.scala.exercises.stdlib.classes.Point
import com.deepakm.scala.exercises.stdlib.options.MyOption
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListsSuite extends AnyFunSuite {
  test("list equality using eq, tests identity same object") {
    val a = List(1, 2, 3)
    val b = List(1, 2, 3)
    (a eq b) should be(false)
  }

  test("list equality using ==, tests equality, the same content") {
    val a = List(1, 2, 3)
    val b = List(1, 2, 3)
    (a == b) should be(true)
  }

  test("Nil lists are identical, even of different types") {
    val a: List[String] = Nil
    val b: List[Int] = Nil

    (a == Nil) should be(true)
    (a eq Nil) should be(true)

    (b == Nil) should be(true)
    (b eq Nil) should be(true)

    (a == b) should be(true)
    (a eq b) should be(true)
  }

  test("Lists can be easily created") {
    val a = List(1, 2, 3)
    a should equal(List(1, 2, 3))
  }

  test("accessing lists via head, headOption and tail. ") {
    val a = List(1, 2, 3)
    a.headOption should equal(Some(1))
    a.tail should equal(List(2, 3))
  }

  test("Lists can be accessed by position") {
    val a = List(1, 2, 3, 4, 5)
    a(0) should be(1)
    a(2) should be(3)
    a(4) should equal(5)
    intercept[IndexOutOfBoundsException] {
      println(a(5))
    }
  }

  test("Lists are immutable") {
    val a = List(1, 3, 5, 7, 9)
    val b = a.filterNot(v => v == 5)

    a should equal(List(1, 3, 5, 7, 9))
    b should equal(List(1, 3, 7, 9))
  }

  test("Lists have many utility methods") {
    val a = List(1, 5, 10)

    //get the length of the list
    a.length should equal(3)

    //reverse the list
    a.reverse should equal(List(10, 5, 1))

    //map a function to doublr the number over the list
    a.map {
      v => v * 2
    } should equal(List(2, 10, 20))

    //filter any values divisible by 3 in the list
    a.filter { v => v % 3 == 0 } should equal(List())
  }

  test("Functions over lists can use _ as shorthand") {
    val a = List(1, 5, 10)
    //map a function to doublr the number over the list
    a.map {
      _ * 2
    } should equal(List(2, 10, 20))

    //filter any values divisible by 3 in the list
    a.filter {
      _ % 3 == 0
    } should equal(List())
  }

  test("Functions over lists can use () instead of {}") {
    val a = List(1, 2, 3)
    a.map(_ * 2) should equal(List(2, 4, 6))
    a.filter(_ % 2 != 0) should equal(List(1, 3))
  }

  test("Lists can be reduced with a mathematical operation: ") {
    val a = List(1, 3, 5, 7)
    a.reduceLeft(_ + _) should equal(16)
    a.reduceLeft(_ * _) should equal(105)
  }

  test("foldLeft is like reduce, but with an explicit starting value: ") {
    val a = List(1, 3, 5, 7)
    // NOTE: foldLeft uses a form called currying that we will explore later
    a.foldLeft(0)(_ + _) should equal(16)
    a.foldLeft(10)(_ + _) should equal(26)
    a.foldLeft(1)(_ * _) should equal(105)
    a.foldLeft(0)(_ * _) should equal(0)
  }

  test("You can create a list from a range: ") {
    val a = (1 to 5).toList
    a should be(List(1, 2, 3, 4, 5))
  }

  test("You can prepend elements to a List to get a new List: ") {
    val a = List(1, 3, 5, 7)

    0 :: a should be(List(0, 1, 3, 5, 7))
  }

  test("Lists can be concatened and Nil is an empty List: ") {
    val head = List(1, 3)
    val tail = List(5, 7)

    head ::: tail should be(List(1, 3, 5, 7))
    head ::: Nil should be(List(1, 3))
  }

  test("Lists reuse their tails: ") {
    val d = Nil
    val c = 3 :: d
    val b = 2 :: c
    val a = 1 :: b

    a should be(List(1, 2, 3))
    a.tail should be(List(2, 3))
    b.tail should be(List(3))
    c.tail should be(Nil)
  }
}
