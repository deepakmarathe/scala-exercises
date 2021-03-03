package com.deepakm.scala.exercise.shapeless

import cats.~>
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

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
class PolymorphicFunctionValuesSuite extends AnyFunSuite {

  object choose extends (Seq ~> Option) {
    def apply[T](s: Seq[T]) = s.headOption
  }

  test("hello") {
    1 should be(1)

    choose(Seq(1, 2, 3)) should be(Some(1))
    choose(Seq('a', 'b', 'c')) should be(Some('a'))

    def pairApply(f: Seq ~> Option) = (f(Seq(1, 2, 3)), f(Seq('a', 'b', 'c')))

    pairApply(choose) should be(Some(1), Some('a'))
  }
  test(""){
//    (List(Seq(1, 3, 5), Seq(2, 4, 6)) map choose) should be(List(1, 2))
  }

  test("a"){
//    object size extends Poly1 {
    //      implicit def caseInt = at[Int](x => 1)
    //      implicit def caseString = at[String](_.length)
    //      implicit def caseTuple[T, U](implicit st: Case.Aux[T, Int], su: Case.Aux[U, Int]) =
    //        at[(T, U)](t => size(t._1) + size(t._2))
    //    }
    //
    //    size(23) should be()
    //    size("foo") should be()
    //    size((23, "foo")) should be()
    //    size(((23, "foo"), 13)) should be()
  }
}
