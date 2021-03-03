package com.deepakm.scala.exercise.cats.apply

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner
import cats.{Apply, Functor, Monoid, Semigroup, ~>}
import org.scalatest.matchers.must.Matchers.be
import cats.implicits._

@RunWith(classOf[JUnitRunner])
class ApplySuite extends AnyFunSuite {
  val intToString: Int => String = _.toString
  val double: Int => Int = _ * 2
  val addTwo: Int => Int = _ + 2
  val addArity2 = (a: Int, b: Int) => a + b
  val addArity3 = (a: Int, b: Int, c: Int) => a + b + c

  test(" map\n\nSince Apply extends Functor, we can use the map method from Functor: ") {
    import cats.implicits._

    Apply[Option].map(Some(1))(intToString) should be(Some("1"))
    Apply[Option].map(Some(1))(double) should be(Some(2))
    Apply[Option].map(None)(addTwo) should be(None)
  }

  test(" compose\n\nAnd like functors, Apply instances also compose:\n") {
    val listOpt = Apply[List] compose Apply[Option]
    val plusOne = (x: Int) => x + 1
    listOpt.ap(List(Some(plusOne)))(List(Some(1), None, Some(3))) should be(List(Some(2), None, Some(4)))
  }

  test(" ap\n\nThe ap method is a method that Functor does not have: ") {
    Apply[Option].ap(Some(intToString))(Some(1)) should be(Some("1"))
    Apply[Option].ap(Some(double))(Some(1)) should be(Some(2))
    Apply[Option].ap(Some(double))(None) should be(None)
    Apply[Option].ap(None)(Some(1)) should be(None)
    Apply[Option].ap(None)(None) should be(None)
  }

  test("Note that if any of the arguments of this example is None, the final result is None as well. The effects of the context we are operating on are carried through the entire computation: ") {
    Apply[Option].ap2(Some(addArity2))(Some(1), Some(2)) should be(Some(3))
    Apply[Option].ap2(Some(addArity2))(Some(1), None) should be(None)
    Apply[Option].ap3(Some(addArity3))(Some(1), Some(2), Some(3)) should be(Some(6))
  }

  test(" map2, map3, etc\n\nSimilarly, mapN functions are available: ") {
    Apply[Option].map2(Some(1), Some(2))(addArity2) should be(Some(3))
    Apply[Option].map3(Some(1), Some(2), Some(3))(addArity3) should be(Some(6))
  }

  test("Similarly, tupleN functions are available:\n"){
    Apply[Option].tuple2(Some(1), Some(2)) should be(Some(1,2))
    Apply[Option].tuple3(Some(1), Some(2), Some(3)) should be(Some(1,2,3))
  }

  test(" apply builder syntax\n\nIn order to use functions apN, mapN and tupleN *infix*, import cats.implicits._.\n"){
    import cats.implicits._
    val option2 = (Option(1), Option(2))
    val option3 = (option2._1, option2._2, Option.empty[Int])

    option2 mapN addArity2 should be(Some(3))
    option3 mapN addArity3 should be(None)

   option2 apWith Some(addArity2) should be(Some(3))
    option3 apWith Some(addArity3) should be(None)

    option2.tupled should be(Option(1,2))
    option3.tupled should be(None)
  }
}
