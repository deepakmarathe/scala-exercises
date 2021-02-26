package com.deepakm.scala.exercise.stdlib.typevariance

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

import scala.language.postfixOps

@RunWith(classOf[JUnitRunner])
class TypeVarianceSuite extends AnyFunSuite {

  trait Fruit

  trait Citrus

  class Orange extends Fruit with Citrus

  class Tangelo extends Fruit with Citrus

  class Banana extends Fruit
  class Apple extends Fruit

  test("Using type inference the type that you instantiate will be the val or var reference type: ") {
    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket = new MyContainer(new Orange())
    fruitBasket.contents should be("Orange")
  }

  test("You can explicitly declare the type variable of the object during instantiation: ") {
    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }
    val fruitBasket = new MyContainer[Fruit](new Orange())
    fruitBasket.contents should be("Fruit")
  }

  test("You can coerce your object to a type: ") {
    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket: MyContainer[Fruit] = new MyContainer(new Orange())
    fruitBasket.contents should be("Fruit")
  }

  test("Now if you assign a type to the instantiation that is different to the variable type, you'll have " +
    "problems.") {
    /**
     * So, how do we get to set a Fruit basket to an Orange basket? You make it covariant using +. This will allow you
     * to set the container to either a variable with the same type or parent type. In other words, you can assign
     * MyContainer[Fruit] or MyContainer[Citrus].
     */
    class MyContainer[+A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket: MyContainer[Fruit] = new MyContainer[Orange](new Orange())
    fruitBasket.contents should be("Orange")
  }

  test("The problem with covariance is that you can't mutate, set or change the object since it has to " +
    "guarantee that what you put into it is a valid type. In other words the reference is a fruit basket, but we still " +
    "have to make sure that no other fruit can be placed in our orange basket: ") {
    class MyContainer[+A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket: MyContainer[Fruit] = new MyContainer[Orange](new Orange())
    fruitBasket.contents should be("Orange")

    class NavelOrange extends Orange //Creating a subtype to prove a point
    //    val navelOrangeBasket: MyContainer[NavelOrange] = new MyContainer[Orange](new Orange()) //Bad!
    //    val tangeloBasket: MyContainer[Tangelo] = new MyContainer[Orange](new Orange()) //Bad!
  }


  test("Declaring - indicates contravariance variance. Using - you can apply any container with a certain type" +
    " to a container with a superclass of that type. This is reverse to covariant. In our example, we can set a citrus" +
    " basket to an orange or tangelo basket. Since an orange or tangelo basket are a citrus basket. Contravariance is " +
    "the opposite of covariance:") {
    class MyContainer[-A](a: A)(implicit manifest: scala.reflect.Manifest[A]) { //Can't receive a val because it would be in a covariant position
      def contents = manifest.runtimeClass.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.contents should be("Citrus")

    val orangeBasket: MyContainer[Orange] = new MyContainer[Citrus](new Tangelo)
    orangeBasket.contents should be("Citrus")

    val tangeloBasket: MyContainer[Tangelo] = new MyContainer[Citrus](new Orange)
    tangeloBasket.contents should be("Citrus")

    val bananaBasket: MyContainer[Banana] = new MyContainer[Fruit](new Apple)
    bananaBasket.contents should be("Fruit")
  }

  test("Invariance means you need to specify the type exactly: ") {
    class MyContainer[A](val a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      def contents = manifest.runtimeClass.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.contents should be("Citrus")
  }
}
