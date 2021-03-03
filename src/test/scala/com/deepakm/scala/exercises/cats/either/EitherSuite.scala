package com.deepakm.scala.exercises.cats.either

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
class EitherSuite extends AnyFunSuite {
  test("More often than not we want to just bias towards one side and call it a day - by convention, the right side is most often chosen. ") {
    val right: Either[String, Int] = Either.right(5)
    right.map(_ + 1) should be(Either.right(6))

    val left: Either[String, Int] = Either.left("Something went wrong")
    left.map(_ + 1) should be(Either.left("Something went wrong"))
  }

  test("So the flatMap method is right-biased: ") {
    val right: Either[String, Int] = Either.right(5)
    right.flatMap(x => Either.right(x + 1)) should be(Either.right(6))

    val left: Either[String, Int] = Either.left("Something went wrong")
    left.flatMap(x => Either.right(x + 1)) should be(Left("Something went wrong"))
  }

  object EitherStyle {
    def parse(s: String): Either[NumberFormatException, Int] =
      if (s.matches("-?[0-9]+")) Either.right(s.toInt)
      else Either.left(new NumberFormatException(s"${s} is not a valid integer."))

    def reciprocal(i: Int): Either[IllegalArgumentException, Double] =
      if (i == 0) Either.left(new IllegalArgumentException("Cannot take reciprocal of 0."))
      else Either.right(1.0 / i)

    def stringify(d: Double): String = d.toString

    def magic(s: String): Either[Exception, String] =
      parse(s).flatMap(reciprocal).map(stringify)
  }

  test("Using EITHER instead of Exceptions") {
    EitherStyle.parse("Not a number").isRight should be(false)
    EitherStyle.parse("2").isRight should be(true)
  }

  test("Now, using combinators like flatMap and map, we can compose our functions together. Will the following incantations return a Right value? "){
    import EitherStyle._

    magic("0").isRight should be(false)
    magic("1").isRight should be(true)
    magic("Not a number").isRight should be(false)
  }

  test("Still room to improve"){
    import EitherStyle._

    val result = magic("2") match {
      case Left(_: NumberFormatException) => "Not a number!"
      case Left(_: IllegalArgumentException) => "Can't take reciprocal of 0!"
      case Left(_) => "Unknown error"
      case Right(result) => s"Got reciprocal: ${result}"
    }

    result should be("Got reciprocal: 0.5")
  }

  test("Instead of using exceptions as our error value, let's instead enumerate explicitly the things that can go wrong in our program."){
    object EitherStyleWithAdts {
      sealed abstract class Error
      final case class NotANumber(string: String) extends Error
      final case object NoZeroReciprocal extends Error

      def parse(s: String): Either[Error, Int] =
        if (s.matches("-?[0-9]+")) Either.right(s.toInt)
        else Either.left(NotANumber(s))

      def reciprocal(i: Int): Either[Error, Double] =
        if (i == 0) Either.left(NoZeroReciprocal)
        else Either.right(1.0 / i)

      def stringify(d: Double): String = d.toString

      def magic(s: String): Either[Error, String] =
        parse(s).flatMap(reciprocal).map(stringify)
    }

    import EitherStyleWithAdts._

    val result = magic("2") match {
      case Left(NotANumber(_)) => "Not a number!"
      case Left(NoZeroReciprocal) => "Can't take reciprocal of 0!"
      case Right(result) => s"Got reciprocal: ${result}"
    }
    result should be("Got reciprocal: 0.5")
  }

  test("Either in the small, Either in the large "){
    sealed abstract class DatabaseError
    trait DatabaseValue

    object Database {
      def databaseThings(): Either[DatabaseError, DatabaseValue] = ???
    }

    sealed abstract class ServiceError
    trait ServiceValue

    object Service {
      def serviceThings(v: DatabaseValue): Either[ServiceError, ServiceValue] = ???
    }

    def doApp = Database.databaseThings().flatMap(Service.serviceThings)
  }

  test("Solution 1: Application-wide errors "){
    sealed abstract class AppError
    final case object DatabaseError1 extends AppError
    final case object DatabaseError2 extends AppError
    final case object ServiceError1 extends AppError
    final case object ServiceError2 extends AppError

    trait DatabaseValue

    object Database {
      def databaseThings(): Either[AppError, DatabaseValue] = ???
    }

    trait ServiceValue

    object Service {
      def serviceThings(v: DatabaseValue): Either[AppError, ServiceValue] = ???
    }

    def doApp = Database.databaseThings().flatMap(Service.serviceThings)
  }

  test("Solution 2: ADTs all the way down "){
    sealed abstract class DatabaseError
    trait DatabaseValue

    object Database {
      def databaseThings(): Either[DatabaseError, DatabaseValue] = ???
    }

    sealed abstract class ServiceError
    trait ServiceValue

    object Service {
      def serviceThings(v: DatabaseValue): Either[ServiceError, ServiceValue] = ???
    }

    sealed abstract class AppError
    object AppError {
      final case class Database(error: DatabaseError) extends AppError
      final case class Service(error: ServiceError) extends AppError
    }

    def doApp: Either[AppError, ServiceValue] =
      Database.databaseThings().leftMap(AppError.Database).
        flatMap(dv => Service.serviceThings(dv).leftMap(AppError.Service))

    def awesome =
      doApp match {
        case Left(AppError.Database(_)) => "something in the database went wrong"
        case Left(AppError.Service(_)) => "something in the service went wrong"
        case Right(_) => "everything is alright!"
      }

    val right: Either[String, Int] = Right(41)
    right.map(_ + 1) should be(Right(42))

    val left: Either[String, Int] = Left("Hello")
    left.map(_ + 1) should be(Left("Hello"))
    left.leftMap(_.reverse) should be(Left("olleH"))

//    val either: Either[NumberFormatException, Int] =
//      try {
//        Either.right("abc".toInt)
//      } catch {
//        case nfe: NumberFormatException => Either.left(nfe)
//      }

    val either: Either[NumberFormatException, Int] =
      Either.catchOnly[NumberFormatException]("abc".toInt)

    Either.catchOnly[NumberFormatException]("abc".toInt).isRight should be(false)
    Either.catchOnly[NumberFormatException]("abc".toInt).isLeft should be(true)

    Either.catchNonFatal(1 / 0).isLeft should be(true)
  }

  test(" Additional syntax\n\nFor using Either's syntax on arbitrary data types, you can import cats.implicits._. This will make possible to use the asLeft and asRight methods:"){
    import cats.implicits._

    val right: Either[String, Int] = 42.asRight[String]
    right should be(Right(42))
  }
}
