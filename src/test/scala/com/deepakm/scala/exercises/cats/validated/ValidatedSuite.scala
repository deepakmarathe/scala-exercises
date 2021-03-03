package com.deepakm.scala.exercises.cats.validated

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
import cats.{Apply, Semigroup}
import cats.data.ValidatedNel
import org.scalatest.matchers.must.Matchers.be
import cats.implicits._

@RunWith(classOf[JUnitRunner])
class ValidatedSuite extends AnyFunSuite {

  trait Read[A] {
    def read(s: String): Option[A]
  }

  object Read {
    def apply[A](implicit A: Read[A]): Read[A] = A

    implicit val stringRead: Read[String] =
      new Read[String] {
        def read(s: String): Option[String] = Some(s)
      }

    implicit val intRead: Read[Int] =
      new Read[Int] {
        def read(s: String): Option[Int] =
          if (s.matches("-?[0-9]+")) Some(s.toInt)
          else None
      }
  }

  sealed abstract class ConfigError

  final case class MissingConfig(field: String) extends ConfigError

  final case class ParseError(field: String) extends ConfigError

  import cats.data.Validated
  import cats.data.Validated.{Invalid, Valid}

  case class Config(map: Map[String, String]) {
    def parse[A: Read](key: String): Validated[ConfigError, A] =
      map.get(key) match {
        case None => Invalid(MissingConfig(key))
        case Some(value) =>
          Read[A].read(value) match {
            case None => Invalid(ParseError(key))
            case Some(a) => Valid(a)
          }
      }
  }

  import cats.Semigroup

  def parallelValidate[E: Semigroup, A, B, C](v1: Validated[E, A], v2: Validated[E, B])(f: (A, B) => C): Validated[E, C] =
    (v1, v2) match {
      case (Valid(a), Valid(b)) => Valid(f(a, b))
      case (Valid(_), i@Invalid(_)) => i
      case (i@Invalid(_), Valid(_)) => i
      case (Invalid(e1), Invalid(e2)) => Invalid(Semigroup[E].combine(e1, e2))
    }

  import cats.SemigroupK
  import cats.data.NonEmptyList
  import cats.implicits._

  implicit val nelSemigroup: Semigroup[NonEmptyList[ConfigError]] =
    SemigroupK[NonEmptyList].algebra[ConfigError]

  implicit val readString: Read[String] = Read.stringRead
  implicit val readInt: Read[Int] = Read.intRead

  case class ConnectionParams(url: String, port: Int)

  val config = Config(Map(("url", "127.0.0.1"), ("port", "1337")))

  val valid = parallelValidate(
    config.parse[String]("url").toValidatedNel,
    config.parse[Int]("port").toValidatedNel)(ConnectionParams.apply)

  import cats.Applicative

  //  implicit def validatedApplicative[E: Semigroup]: Applicative[Validated[E, *]] =
  //    new Applicative[Validated[E, *]] {
  //      def ap[A, B](f: Validated[E, A => B])(fa: Validated[E, A]): Validated[E, B] =
  //        (fa, f) match {
  //          case (Valid(a), Valid(fab)) => Valid(fab(a))
  //          case (i@Invalid(_), Valid(_)) => i
  //          case (Valid(_), i@Invalid(_)) => i
  //          case (Invalid(e1), Invalid(e2)) => Invalid(Semigroup[E].combine(e1, e2))
  //        }
  //
  //      def pure[A](x: A): Validated[E, A] = Validated.valid(x)
  //
  //      def map[A, B](fa: Validated[E, A])(f: A => B): Validated[E, B] = fa.map(f)
  //
  //      def product[A, B](fa: Validated[E, A], fb: Validated[E, B]): Validated[E, (A, B)] =
  //        ap(fa.map(a => (b: B) => (a, b)))(fb)
  //    }

  test("Parallel validation ") {
    valid.isValid should be(true)
    valid.getOrElse(ConnectionParams("", 0)) should be(ConnectionParams("127.0.0.1", 1337))

    val config1 = Config(Map(("endpoint", "127.0.0.1"), ("port", "not a number")))
    val invalid = parallelValidate(
      config1.parse[String]("url").toValidatedNel,
      config1.parse[Int]("port").toValidatedNel)(ConnectionParams.apply)

    import cats.data.Validated
    import cats.data.NonEmptyList

    invalid.isValid should be(false)
    val errors = NonEmptyList(MissingConfig("url"), List(ParseError("port")))
    invalid == Validated.invalid(errors) should be(true)
  }

  test("Can we perhaps define an Apply instance for Validated? Better yet, can we define an Applicative instance?") {
    case class Person(name: String, age: Int, address: Address)
    case class Address(houseNumber: String, street: String)
    //    val personFromConfig: ValidatedNel[ConfigError, Person] =
    //      Apply[ValidatedNel[ConfigError, *]].map4(
    //        config.parse[String]("name").toValidatedNel,
    //        config.parse[Int]("age").toValidatedNel,
    //        config.parse[Int]("house_number").toValidatedNel,
    //        config.parse[String]("street").toValidatedNel) {
    //        case (name, age, houseNumber, street) => Person(name, age, Address(houseNumber, street))
    //      }

    trait Monad[F[_]] {
      def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

      def pure[A](x: A): F[A]

      def map[A, B](fa: F[A])(f: A => B): F[B] =
        flatMap(fa)(f.andThen(pure))

      def ap[A, B](fa: F[A])(f: F[A => B]): F[B] =
        flatMap(fa)(a => map(f)(fab => fab(a)))
    }

//    val v = validatedMonad.tuple2(Validated.invalidNel[String, Int]("oops"), Validated.invalidNel[String, Double]("uh oh"))

  }

  test("validations"){
    val config = Config(Map("house_number" -> "42"))

    val houseNumber = config.parse[Int]("house_number").andThen { n =>
      if (n >= 0) Validated.valid(n)
      else Validated.invalid(ParseError("house_number"))
    }

    houseNumber.isValid should be(true)
    val error = ParseError("house_number")
    houseNumber == Validated.invalid(error) should be(false)
  }

  test(" withEither\n\nThe withEither method allows you to temporarily turn a Validated instance into an Either instance and apply it to a function."){
    def positive(field: String, i: Int): Either[ConfigError, Int] = {
      if (i >= 0) Either.right(i)
      else Either.left(ParseError(field))
    }
    val config = Config(Map("house_number" -> "-42"))

    val houseNumber = config.parse[Int]("house_number").withEither {
      either: Either[ConfigError, Int] => either.flatMap(i => positive("house_number", i))
    }

    houseNumber.isValid should be(false)
    val error = ParseError("house_number")
    houseNumber == Validated.invalid(error) should be(true)
  }
}