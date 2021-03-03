package com.deepakm.scala.exercise.cats.monad

import cats.Monad
import cats.data.OptionT
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MonadSuite extends AnyFunSuite {
  test("flatten") {
    Option(Option(1)).flatten should be(Option(1))
    Option(None).flatten should be(None)
    List(List(1), List(2, 3)).flatten should be(List(1, 2, 3))
  }

  test("Cats already provides a Monad instance of Option. ") {
    import cats._
    import cats.implicits._

    Monad[Option].pure(42) should be(Option(42))
  }

  test(" flatMap\n\nflatMap is often considered to be the core function of Monad, and cats follows this tradition by providing implementations of flatten and map derived from flatMap and pure.") {
    import cats._
    import cats.implicits._

    Monad[List].flatMap(List(1, 2, 3))(x => List(x, x)) should be(List(1, 1, 2, 2, 3, 3))
  }

  test(" ifM\n\nMonad provides the ability to choose later operations in a sequence based on the results of earlier ones. This is embodied in ifM, which lifts an if statement into the monadic context. ") {
    import cats._
    import cats.implicits._

    Monad[Option].ifM(Option(true))(Option("truthy"), Option("falsy")) should be(Option("truthy"))
    Monad[List].ifM(List(true, false, true))(List(1, 2), List(3, 4)) should be(List(1, 2, 3, 4, 1, 2))
  }

  test("Composition"){
//    case class OptionT[F[_], A](value: F[Option[A]])
//
//    implicit def optionTMonad[F[_]](implicit F: Monad[F]) = {
//      new Monad[OptionT[F, *]] {
//        def pure[A](a: A): OptionT[F, A] = OptionT(F.pure(Some(a)))
//        def flatMap[A, B](fa: OptionT[F, A])(f: A => OptionT[F, B]): OptionT[F, B] =
//          OptionT {
//            F.flatMap(fa.value) {
//              case None => F.pure(None)
//              case Some(a) => f(a).value
//            }
//          }
//        def tailRecM[A, B](a: A)(f: A => OptionT[F, Either[A, B]]): OptionT[F, B] =
//          defaultTailRecM(a)(f)
//      }
//    }
    import cats.implicits._
    //optionTMonad[List].pure(42) should be(OptionT(List(42)))
  }
}
