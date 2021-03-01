package com.deepakm.scala.exercises.cats.semigroups

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner
import cats.Semigroup
import org.scalatest.matchers.must.Matchers.be
import cats.implicits._

@RunWith(classOf[JUnitRunner])
class SemiGroupsSuite extends AnyFunSuite {
  test("Now that you've learned about the Semigroup instance for Int try to guess how it works in the following examples: ") {
    Semigroup[Int].combine(1, 2) should be(3)
    Semigroup[List[Int]].combine(List(1, 2, 3), List(4, 5, 6)) should be(List(1, 2, 3, 4, 5, 6))
    Semigroup[Option[Int]].combine(Option(1), Option(2)) should be(Some(3))
    Semigroup[Option[Int]].combine(Option(1), None) should be(Some(1))
  }

  test("And now try a slightly more complex combination: "){
    import cats.implicits._

    Semigroup[Int => Int].combine(_ + 1, _ * 10).apply(6) should be(67)
  }

  test("since the first version uses the Semigroup's combine operation, it will merge the map's values with combine. "){
    import cats.implicits._

    val aMap = Map("foo" -> Map("bar" -> 5))
    val anotherMap = Map("foo" -> Map("bar" -> 6))
    val combinedMap = Semigroup[Map[String, Map[String, Int]]].combine(aMap, anotherMap)

    combinedMap.get("foo") should be(Some(Map("bar" -> 11)))
  }

  test("There is inline syntax available for Semigroup. Here we are following the convention from scalaz, that |+| is the operator from Semigroup."){
    import cats.implicits._

    val one: Option[Int] = Option(1)
    val two: Option[Int] = Option(2)
    val n: Option[Int] = None

    one |+| two should be(Option(3))
    n |+| two should be(Option(2))
    n |+| n should be(None)
    two |+| n should be(Option(2))
  }
}
