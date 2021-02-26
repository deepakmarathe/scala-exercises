package com.deepakm.scala.exercise.stdlib.typesignatures

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

import scala.language.postfixOps

@RunWith(classOf[JUnitRunner])
class TypeSignaturesSuite extends AnyFunSuite{
  test("In Java you declare a generic type within a <>, in Scala [] is used:"){
    val z: List[String] = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: "Te" :: "Do" :: Nil
    z should be(z)
    z.isInstanceOf[List[String]] should be(true)
  }

  test("Most of the time, Scala will infer the type and [] are optional:"){
    val z = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: "Te" :: "Do" :: Nil //Infers that the list assigned to variable is of type List[String]
    z.isInstanceOf[List[String]] should be(true)
  }

  test("A trait can be declared containing a type, where a concrete implementer will satisfy the type: "){
    trait Randomizer[A] {
      def draw(): A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw() = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    (intRand.draw <= Int.MaxValue) should be(true)
  }

  test("Class meta-information can be retrieved by class name by using classOf[className]: "){
    classOf[String].getCanonicalName should be("java.lang.String")
    classOf[String].getSimpleName should be("String")
  }

  test("Class meta-information can be derived from an object reference using getClass(): "){
    val zoom = "zoom"
    zoom.isInstanceOf[String] should be(true)
    zoom.getClass.getCanonicalName should be("java.lang.String")
    zoom.getClass.getSimpleName should be("String")
  }

  test("isInstanceOf[className] is used to determine if an object reference is an instance of a given class: "){
    trait Randomizer[A] {
      def draw(): A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw() = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    intRand.isInstanceOf[IntRandomizer] should be(true)
    intRand.isInstanceOf[Randomizer[Int]] should be(true)
    intRand.draw.isInstanceOf[Int] should be(true)
  }
}
