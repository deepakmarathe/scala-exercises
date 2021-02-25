package com.deepakm.scala.exercise.stdlib.infixtypes

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InfixTypesSuite extends AnyFunSuite {
  test("") {
    case class Person(name: String)
    class Loves[A, B](val a: A, val b: B)

    def announceCouple(couple: Loves[Person, Person]) =
    //Notice our type: Person loves Person!
      couple.a.name + " is in love with " + couple.b.name

    def announceCouple2(couple: Person Loves Person) =
    //Notice our type: Person loves Person!
      couple.a.name + " is in love with " + couple.b.name

    val romeo = new Person("Romeo")
    val juliet = new Person("Juliet")

    announceCouple(new Loves(romeo, juliet)) should be("Romeo is in love with Juliet")
    announceCouple2(new Loves(romeo, juliet)) should be("Romeo is in love with Juliet")
  }

  test("creating an infix operator method to use with our infix type: ") {
    case class Person(name: String) {
      def loves(person: Person) = new Loves(this, person)
    }

    class Loves[A, B](val a: A, val b: B)

    def announceCouple(couple: Person Loves Person) =
    //Notice our type: Person loves Person!
      couple.a.name + " is in love with " + couple.b.name

    val romeo = new Person("Romeo")
    val juliet = new Person("Juliet")

    announceCouple(romeo loves juliet) should be("Romeo is in love with Juliet")
  }
}
