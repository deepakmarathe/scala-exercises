package com.deepakm.scala.exercise.stdlib.iterables

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IterablesSuite extends AnyFunSuite {
  test("test iterator") {
    val list = List(3, 5, 9, 11, 15, 19, 21)
    val it = list.iterator
    if (it.hasNext)
      it.next should be(3)
  }

  test("grouped will return fixed-size Iterable chunks of an Iterable: ") {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list grouped 3
    it.next() should be(List(3, 5, 9))
    it.next() should be(List(11, 15, 19))
    it.next() should be(List(21, 24, 32))
  }

  test("sliding will return an Iterable that shows a sliding window of an Iterable.") {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list sliding 3
    it.next() should be(List(3, 5, 9))
    it.next() should be(List(5, 9, 11))
    it.next() should be(List(9, 11, 15))
  }

  test("sliding can take the size of the window as well the size of the step during each iteration: ") {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list sliding(3, 3)
    it.next() should be(List(3, 5, 9))
    it.next() should be(List(11, 15, 19))
    it.next() should be(List(21, 24, 32))
  }

  test("takeRight is the opposite of 'take' in Traversable. It retrieves the last elements of an Iterable: ") {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    (list takeRight 3) should be(List(21, 24, 32))
  }

  test("dropRight will drop a specified number of elements from the right: ") {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    (list dropRight 3) should be(List(3, 5, 9, 11, 15, 19))
  }

  test("zip will stitch two iterables into an iterable of pairs of corresponding elements from both iterables.") {
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann", "Stella")
    (xs zip ys) should be(List((3, "Bob"), (5, "Ann"), (9, "Stella")))
  }

  test("If two Iterables aren't the same size, then zip will only zip what can be paired.") {
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann")
    (xs zip ys) should be(List((3, "Bob"), (5, "Ann")))
  }

  test("If two Iterables aren't the same size, then zipAll can provide fillers for what it couldn't find a complement for.") {
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann")
    (xs zipAll(ys, -1, "?")) should be(List((3, "Bob"), (5, "Ann"), (9, "?")))

    val xt = List(3, 5)
    val yt = List("Bob", "Ann", "Stella")
    (xt zipAll(yt, -1, "?")) should be(List((3, "Bob"), (5, "Ann"), (-1, "Stella")))
  }

  test("zipWithIndex will zip an Iterable with its integer index: "){
    val xs = List("Manny", "Moe", "Jack")
    xs.zipWithIndex should be(List(("Manny", 0), ("Moe", 1), ("Jack", 2)))
  }

  test("sameElements will return true if the two Iterables produce the same elements in the same order. The iterator for a set created with less than 5 values will return elements in the order in which they were added, rather than the consistent, hash-based ordering used by iterators for larger Sets: "){
    val xs = List("Manny", "Moe", "Jack")
    val ys = List("Manny", "Moe", "Jack")
    xs.iterator.sameElements(ys) should be(true)

    val xt = List("Manny", "Moe", "Jack")
    val yt = List("Manny", "Jack", "Moe")
    xt.iterator.sameElements(yt) should be(false)

    val xs1 = Set(3, 2, 1, 4, 5, 6, 7)
    val ys1 = Set(7, 2, 1, 4, 5, 6, 3)
    xs1.iterator.sameElements(ys1) should be(true)

    val xt1 = Set(1, 2, 3)
    val yt1 = Set(3, 2, 1)
    xt1.iterator.sameElements(yt1) should be(false)
  }
}
