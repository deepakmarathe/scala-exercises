package com.deepakm.scala.exercise.cats.functor

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner
import cats.{Functor, Monoid, Semigroup, ~>}
import org.scalatest.matchers.must.Matchers.be
import cats.implicits._

@RunWith(classOf[JUnitRunner])
class FunctorSuite extends AnyFunSuite{
  test("Using functor :  map\n\nList is a functor which applies the function to each element of the list:"){
    Functor[List].map(List("qwer", "adsfg"))(_.length) should be(List(4,5))
  }

  test("Option is a functor which only applies the function when the Option value is a Some: "){
    Functor[Option].map(Option("Hello"))(_.length) should be(Some(5))
    Functor[Option].map(None: Option[String])(_.length) should be(None)
  }

  test("lift : We can now apply the lenOption function to Option instances. "){
    val lenOption: (Option[String] => Option[Int]) = Functor[Option].lift(_.length)
    lenOption(Some("Hello")) should be(Some(5))
  }

  test(" fproduct\n\nFunctor provides an fproduct function which pairs a value with the result of applying a function to that value. "){
    val source = List("Cats", "is", "awesome")
    val product = Functor[List].fproduct(source)(_.length).toMap

    product.get("Cats").getOrElse(0) should be(4)
    product.get("is").getOrElse(0) should be(2)
    product.get("awesome").getOrElse(0) should be(7)
  }

  test(" compose\n\nFunctors compose! Given any functor F[_] and any functor G[_] we can create a new functor F[G[_]] by composing them:"){
    val listOpt = Functor[List] compose Functor[Option]
    listOpt.map(List(Some(1), None, Some(3)))(_ + 1) should be(List(Some(2), None, Some(4)))
  }
}
