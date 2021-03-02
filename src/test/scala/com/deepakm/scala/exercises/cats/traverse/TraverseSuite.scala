package com.deepakm.scala.exercises.cats.traverse

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
import cats.Semigroup
import org.scalatest.matchers.must.Matchers.be
import cats.implicits._

@RunWith(classOf[JUnitRunner])
class TraverseSuite extends AnyFunSuite {

  import cats.Semigroup
  import cats.data.{NonEmptyList, OneAnd, Validated, ValidatedNel}
  import cats.implicits._

  def parseIntEither(s: String): Either[NumberFormatException, Int] =
    Either.catchOnly[NumberFormatException](s.toInt)

  def parseIntValidated(s: String): ValidatedNel[NumberFormatException, Int] =
    Validated.catchOnly[NumberFormatException](s.toInt).toValidatedNel

  test(" Choose your effect\n\nThe type signature of Traverse appears highly abstract, and indeed it is - what traverse does as it walks the F[A] depends on the effect of the function. Let's see some examples where F is taken to be List.") {
    List("1", "2", "3").traverse(parseIntEither) should be(Right(List(1, 2, 3)))
    List("1", "abc", "3").traverse(parseIntEither).isLeft should be(true)
  }

  test("Now that we've provided such evidence, we can use ValidatedNel as an applicative. "){
    List("1", "2", "3").traverse(parseIntValidated).isValid should be(true)
  }

  test("Sequencing "){
    import cats.implicits._

    List(Option(1), Option(2), Option(3)).traverse(identity) should be(Some(List(1,2,3)))
    List(Option(1), None, Option(3)).traverse(identity) should be(None)
  }

  test("Traversing solely for the sake of the effect (ignoring any values that may be produced, Unit or otherwise) is common, so Foldable (superclass of Traverse) provides traverse_ and sequence_ methods that do the same thing as traverse and sequence but ignores any value produced along the way, returning Unit at the end.\n"){
    import cats.implicits._

    List(Option(1), Option(2), Option(3)).sequence_ should be(Some())
    List(Option(1), None, Option(3)).sequence_ should be(None)
  }
}
