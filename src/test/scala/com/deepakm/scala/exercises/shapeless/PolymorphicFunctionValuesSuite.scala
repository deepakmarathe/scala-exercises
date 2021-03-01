package com.deepakm.scala.exercises.shapeless

import cats.~>
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

@RunWith(classOf[JUnitRunner])
class PolymorphicFunctionValuesSuite extends AnyFunSuite {
  test("hello"){
    1 should be(1)
    object choose extends (Seq ~> Option) {
      def apply[T](s: Seq[T]) = s.headOption
    }
//
    //println(choose(Seq(1, 2, 3)))
//    choose(Seq(1, 2, 3)) should be(Some(1))
//    choose(Seq('a', 'b', 'c')) should be(Some('a'))
  }
}
