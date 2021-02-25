package com.deepakm.scala.exercise.stdlib.sequecesandarrays

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

import java.util.Date

@RunWith(classOf[JUnitRunner])
class SequencesAndArraysSuite extends AnyFunSuite {
  test("A list can be converted to an array: ") {
    val l = List(1, 2, 3)
    val a = l.toArray
    a should equal(Array(1, 2, 3))
  }

  test("Any sequence can be converted to a list: ") {
    val a = Array(1, 2, 3)
    val s = a.toSeq
    val l = s.toList
    l should equal(s)
    l should equal(List(1, 2, 3))
    l should equal(Seq(1, 2, 3))
    s should equal(Seq(1, 2, 3))
  }

  test("You can create a sequence from a for loop: ") {
    val s = for (v <- 1 to 4) yield v
    s.toList should be(Seq(1, 2, 3, 4))
  }

  test("You can create a sequence from a for loop with a filter: ") {
    val s = for (v <- 1 to 10 if v % 3 == 0) yield v
    s.toList should be(Seq(3, 6, 9))
    s.toList should be(List(3, 6, 9))
    s.toSeq should be(List(3, 6, 9))
    s.toSeq should be(Seq(3, 6, 9))
  }

  test("You can filter any sequence based on a predicate: ") {
    val s = Seq("hello", "to", "you")
    val filtered = s.filter(_.length > 2)
    filtered should be(Seq("hello", "you"))
  }

  test("You can also filter Arrays in the same way: "){
    val a = Array("hello", "to", "you", "again")
    val filtered = a.filter(_.length > 3)
    filtered should be(Array("hello", "again"))
  }

  test("You can map values in a sequence through a function: "){
    val s = Seq("hello", "world")
    val r = s map {
      _.reverse
    }

    r should be(List("olleh", "dlrow"))
  }
}
