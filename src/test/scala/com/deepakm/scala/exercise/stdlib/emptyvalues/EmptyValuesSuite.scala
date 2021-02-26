package com.deepakm.scala.exercise.stdlib.emptyvalues

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.must.Matchers.be

@RunWith(classOf[JUnitRunner])
class EmptyValuesSuite extends AnyFunSuite {
  test("An empty list can be represented by another nothing value: Nil") {
    List() === Nil shouldBe true
  }

  test("None equals None: ") {
    None === None shouldBe true
  }

  test("None should be identical to None: ") {
    None eq None shouldBe true
  }

  test("None can be converted to a String: ") {
    assert(None.toString === "None")
  }

  test("None can be converted to an empty list: ") {
    None.toList === Nil shouldBe true
  }

  test("None is considered empty: ") {
    assert(None.isEmpty === true)
  }

  test("None can be cast to Any, AnyRef or AnyVal: ") {
      None.asInstanceOf[Any] === None shouldBe true
      None.asInstanceOf[AnyRef] === None shouldBe true
      None.asInstanceOf[AnyVal] === None shouldBe true
  }

  test("None can be used with Option instead of null references: "){
    val optional: Option[String] = None
    assert(optional.isEmpty === true)
    assert(optional === None)
  }

  test("Some is the opposite of None for Option types: "){
    val optional: Option[String] = Some("Some Value")
    assert((optional == None) === false, "Some(value) should not equal None")
    assert(optional.isEmpty === false, "Some(value) should not be empty")
  }

  test("Option.getOrElse can be used to provide a default in the case of None: "){
    val optional: Option[String] = Some("Some Value")
    val optional2: Option[String] = None
    assert(optional.getOrElse("No Value") === "Some Value", "Should return the value in the option")
    assert(optional2.getOrElse("No Value") === "No Value", "Should return the specified default value")
  }
}
