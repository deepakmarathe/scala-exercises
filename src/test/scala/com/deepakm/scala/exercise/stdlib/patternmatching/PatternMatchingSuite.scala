package com.deepakm.scala.exercise.stdlib.patternmatching

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import com.deepakm.scala.exercises.stdlib.options.MyOption
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PatternMatchingSuite extends AnyFunSuite {
  test("Pattern matching returns something: ") {
    val stuff = "blue"

    val myStuff = stuff match {
      case "red" =>
        println("RED"); 1
      case "blue" =>
        println("BLUE"); 2
      case "green" =>
        println("GREEN"); 3
      case _ =>
        println(stuff); 0 // case _ will trigger if all other cases fail.
    }

    myStuff should be(2)
  }

  test("Pattern matching can return complex values: ") {
    val stuff = "blue"

    val myStuff = stuff match {
      case "red" => (255, 0, 0)
      case "green" => (0, 255, 0)
      case "blue" => (0, 0, 255)
      case _ => println(stuff); 0
    }

    myStuff should be((0, 0, 255))
  }

  test("Pattern matching can match complex expressions: "){
    def goldilocks(expr: Any) =
      expr match {
        case ("porridge", "Papa") => "Papa eating porridge"
        case ("porridge", "Mama") => "Mama eating porridge"
        case ("porridge", "Baby") => "Baby eating porridge"
        case _ => "what?"
      }

    goldilocks(("porridge", "Mama")) should be("Mama eating porridge")
  }

  test("Pattern matching can wildcard parts of expressions: "){
    def goldilocks(expr: Any) =
      expr match {
        case ("porridge", _) => "eating"
        case ("chair", "Mama") => "sitting"
        case ("bed", "Baby") => "sleeping"
        case _ => "what?"
      }

    goldilocks(("porridge", "Papa")) should be("eating")
    goldilocks(("chair", "Mama")) should be("sitting")
  }

  test("Pattern matching can substitute parts of expressions: "){
    def goldilocks(expr: (String, String)) =
      expr match {
        case ("porridge", bear) =>
          bear + " said someone's been eating my porridge"
        case ("chair", bear) => bear + " said someone's been sitting in my chair"
        case ("bed", bear) => bear + " said someone's been sleeping in my bed"
        case _ => "what?"
      }

    goldilocks(("porridge", "Papa")) should be("Papa said someone's been eating my porridge")
    goldilocks(("chair", "Mama")) should be("Mama said someone's been sitting in my chair")
  }

  test("A backquote can be used to refer to a stable variable in scope to create a case statement - this prevents variable shadowing"){
    val foodItem = "porridge"

    def goldilocks(expr: (String, String)) =
      expr match {
        case (`foodItem`, _) => "eating"
        case ("chair", "Mama") => "sitting"
        case ("bed", "Baby") => "sleeping"
        case _ => "what?"
      }

    goldilocks(("porridge", "Papa")) should be("eating")
    goldilocks(("chair", "Mama")) should be("sitting")
    goldilocks(("porridge", "Cousin")) should be("eating")
    goldilocks(("beer", "Cousin")) should be("what?")
  }

  test("A backquote can be used to refer to a method parameter as a stable variable to create a case statement:"){
    def patternEquals(i: Int, j: Int) =
      j match {
        case `i` => true
        case _ => false
      }
    patternEquals(3, 3) should be(true)
    patternEquals(7, 9) should be(false)
    patternEquals(9, 9) should be(true)
  }

  test("pattern match against a list"){
    val secondElement = List(1, 2, 3) match {
      case x :: xs => xs.head
      case _ => 0
    }

    secondElement should be(2)
  }

  test("pattern match against a list : part 2"){
    val secondElement = List(1, 2, 3) match {
      case x :: y :: xs => y
      case _ => 0
    }

    secondElement should be(2)
  }

  test("pattern matching a list with only 1 item"){
    val secondElement = List(1) match {
      case x :: y :: xs => y // only matches a list with two or more items
      case _ => 0
    }

    secondElement should be(0)
  }

  test("To pattern match against List, you can also establish a pattern match if you know the exact number of elements in a List: "){
    val r = List(1, 2, 3) match {
      case x :: y :: Nil => y // only matches a list with exactly two items
      case _ => 0
    }

    r should be(0)
  }

  test("If a pattern is exactly one element longer than a List, it extracts the final Nil: "){
    val r = List(1, 2, 3) match {
      case x :: y :: z :: tail => tail
      case _ => 0
    }

    r == Nil should be(true)
  }
}
