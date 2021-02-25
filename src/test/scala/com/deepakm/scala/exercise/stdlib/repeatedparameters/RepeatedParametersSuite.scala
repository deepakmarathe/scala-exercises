package com.deepakm.scala.exercise.stdlib.repeatedparameters

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

@RunWith(classOf[JUnitRunner])
class RepeatedParametersSuite extends AnyFunSuite {
  def repeatedParameterMethod(x: Int, y: String, z: Any*) = {
    "%d %ss can give you %s".format(x, y, z.mkString(", "))
  }

  test("") {
    repeatedParameterMethod(
      3,
      "egg",
      "a delicious sandwich",
      "protein",
      "high cholesterol") should be("3 eggs can give you a delicious sandwich, protein, high cholesterol")
  }

  test("A repeated parameter can accept a collection as the last parameter but will be considered a single object:") {
    repeatedParameterMethod(
      3,
      "egg",
      List("a delicious sandwich", "protein", "high cholesterol")) should be("3 eggs can give you List(a delicious sandwich, protein, high cholesterol)")
  }

  test("A repeated parameter can accept a collection - if you want it expanded, add :_"){
    repeatedParameterMethod(
      3,
      "egg",
      List("a delicious sandwich", "protein", "high cholesterol"): _*) should be("3 eggs can give you a delicious sandwich, protein, high cholesterol")
  }
}
