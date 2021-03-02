package com.deepakm.scala.exercises.cats.identity

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
import cats.{Applicative, Comonad, Functor, Id, Monoid, Semigroup, ~>}
import org.scalatest.matchers.must.Matchers.be
import cats.implicits._

@RunWith(classOf[JUnitRunner])
class IdentitySuite extends AnyFunSuite{
  test("That is to say that the type Id[A] is just a synonym for A. We can freely trea"){
    import cats._

    val x: Id[Int] = 1
    val y: Int = x

    val anId: Id[Int] = 42
    anId should be(42)
  }

  test("Using this type declaration, we can treat our Id type constructor as a Monad and as a Comonad. The pure method, which has type A => Id[A] just becomes the identity function. The map method from Functor just becomes function application:\n"){
    import cats.Functor

    val one: Int = 1
    Functor[Id].map(one)(_ + 1)

    Applicative[Id].pure(42) should be(42)
  }

  test("You'll notice that in the flatMap signature, since Id[B] is the same as B for all B, we can rewrite the type of the f parameter to be A => B instead of A => Id[B], and this makes the signatures of the two functions the same, and, in fact, they can have the same implementation, meaning that for Id, flatMap is also just function application:"){
    import cats.Monad

    val one: Int = 1
    Monad[Id].map(one)(_ + 1)
    Monad[Id].flatMap(one)(_ + 1)

    val fortytwo: Int = 42
    Comonad[Id].coflatMap(fortytwo)(_ + 1) should be(43)
  }
}
