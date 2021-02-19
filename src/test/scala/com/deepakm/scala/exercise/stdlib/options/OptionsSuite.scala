package com.deepakm.scala.exercise.stdlib.options


import com.deepakm.scala.exercises.stdlib.asserttests.AdditionCommand
import com.deepakm.scala.exercises.stdlib.classes.Point
import com.deepakm.scala.exercises.stdlib.options.MyOption
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OptionsSuite extends AnyFunSuite {

  val someValue: Option[String] = Some("I am wrapped in something!")
  val emptyValue: Option[String] = None
  val myOption = new MyOption

  test("assertions") {
    someValue should be(Some("I am wrapped in something!"))

    emptyValue should be(None)

    val value1 = myOption.maybeItWillReturnSomething(true)
    val value2 = myOption.maybeItWillReturnSomething(false)
    value1 getOrElse "No value" should be("Found value")
    value2 getOrElse "No value" should be("No value")
    value2 getOrElse {
      "default function"
    } should be("default function")

    value1.isEmpty shouldEqual (false)
    value1.isEmpty should be(false)
    value2.isEmpty shouldEqual (true)
    value2.isEmpty should be(true)
  }

  test("double assertions") {
    val someValue: Option[Double] = Some(20.0)
    val value = someValue match {
      case Some(v) => v
      case None => 0.0
    }
    value should be(20.0)
  }
}
