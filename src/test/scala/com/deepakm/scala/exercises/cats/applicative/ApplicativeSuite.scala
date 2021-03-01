package com.deepakm.scala.exercises.cats.applicative

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner
import cats.{Applicative, Apply, Functor, Monad, Monoid, Semigroup, ~>}
import org.scalatest.matchers.must.Matchers.be
import cats.implicits._

@RunWith(classOf[JUnitRunner])
class ApplicativeSuite extends AnyFunSuite{
  test(""){
    import cats._
    import cats.implicits._

    Applicative[Option].pure(1) should be(Some(1))
    Applicative[List].pure(1) should be(List(1))
  }

  test("Like Functor and Apply, Applicative functors also compose naturally with each other. When you compose one Applicative with another, the resulting pure operation will lift the passed value into one context, and the result into the other context: "){
    (Applicative[List] compose Applicative[Option]).pure(1) should be(List(Some(1)))
  }

  test(" Applicative Functors & Monads\n\nApplicative is a generalization of Monad, allowing expression of effectful computations in a pure functional way.\n\nApplicative is generally preferred to Monad when the structure of a computation is fixed a priori. That makes it possible to perform certain kinds of static analysis on applicative values.\n"){
    Monad[Option].pure(1) should be(Some(1))
    Applicative[Option].pure(1) should be(Some(1))
  }
}
