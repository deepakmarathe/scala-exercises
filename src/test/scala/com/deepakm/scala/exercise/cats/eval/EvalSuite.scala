package com.deepakm.scala.exercise.cats.eval

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

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
class EvalSuite extends AnyFunSuite{
  test("eval"){
    import cats.Eval
    // import cats.Eval

    import cats.implicits._
    // import cats.implicits._

    val eager = Eval.now {
      println("Running expensive calculation...")
      1 + 2 * 3
    }

    val eagerEval = Eval.now {
      println("This is eagerly evaluated")
      1 :: 2 :: 3 :: Nil
    }

    //when/then

  }
}
