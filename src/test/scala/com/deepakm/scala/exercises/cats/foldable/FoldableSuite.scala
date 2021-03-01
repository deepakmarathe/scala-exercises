package com.deepakm.scala.exercises.cats.foldable

import cats.{Foldable, Later, Now}
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FoldableSuite extends AnyFunSuite {
  test(" foldLeft\n\nfoldLeft is an eager left-associative fold on F using the given function. ") {
    Foldable[List].foldLeft(List(1, 2, 3), 0)(_ + _) should be(6)
    Foldable[List].foldLeft(List("a", "b", "c"), "")(_ + _) should be("abc")
  }

  test(" foldRight\n\nfoldRight is a lazy right-associative fold on F using the given function. The function has the signature (A, Eval[B]) => Eval[B] to support laziness in a stack-safe way. ") {
    val lazyResult =
      Foldable[List].foldRight(List(1, 2, 3), Now(0))((x, rest) => Later(x + rest.value))
    lazyResult.value should be(6)
  }

  test(" fold\n\nfold, also called combineAll, combines every value in the foldable using the given Monoid instance. "){
    Foldable[List].fold(List("a", "b", "c")) should be("abc")
    Foldable[List].fold(List(1, 2, 3)) should be(6)

  }
}
