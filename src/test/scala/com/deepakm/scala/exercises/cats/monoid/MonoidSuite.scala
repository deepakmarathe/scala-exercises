package com.deepakm.scala.exercises.cats.monoid

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner
import cats.{Monoid, Semigroup, ~>}
import org.scalatest.matchers.must.Matchers.be
import cats.implicits.{_}

@RunWith(classOf[JUnitRunner])
class MonoidSuite extends AnyFunSuite {
  test("Monoid extends the Semigroup type class, adding an empty method to semigroup's combine. The empty method must return a value that when combined with any other instance of that type returns the other instance, i.e.") {
  }

  test("test imports") {
    import cats._
    import cats.implicits._
  }

  test("And let's see the implicit instance of Monoid[String] in action. ") {
    import cats._
    import cats.implicits._

    Monoid[String].empty should be("")
    Monoid[String].combineAll(List("a", "b", "c")) should be("abc")
    Monoid[String].combineAll(List()) should be("")
  }

  test("The advantage of using these type class provided methods, rather than the specific ones for each type," +
    " is that we can compose monoids to allow us to operate on more complex types, e.g. ") {
    Monoid[Map[String, Int]].combineAll(List(Map("a" -> 1, "b" -> 2), Map("a" -> 3))) should be(Map("a" -> 4, "b" -> 2))
    Monoid[Map[String, Int]].combineAll(List()) should be(Map())
  }

  test("This is also true if we define our own instances. As an example, let's use Foldable's foldMap, which maps over values accumulating the results, using the available Monoid for the type mapped onto. ") {
    val l = List(1, 2, 3, 4, 5)
    l.foldMap(identity) should be(15)
    l.foldMap(i => i.toString) should be("12345")
  }

  test("To use this with a function that produces a tuple, we can define a Monoid for a tuple that will be valid for any tuple where the types it contains also have a Monoid available. Note that cats already defines it for you.") {
    val l = List(1, 2, 3, 4, 5)
    l.foldMap(i => (i, i.toString)) should be((15, "12345"))
  }
}
