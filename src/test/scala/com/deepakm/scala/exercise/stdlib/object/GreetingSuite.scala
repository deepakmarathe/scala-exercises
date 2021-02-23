package com.deepakm.scala.exercise.stdlib.`object`

import com.deepakm.scala.exercises.stdlib.`object`.Greeting
import com.deepakm.scala.exercises.stdlib.asserttests.AdditionCommand
import com.deepakm.scala.exercises.stdlib.classes.Point
import com.deepakm.scala.exercises.stdlib.options.MyOption
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GreetingSuite extends AnyFunSuite {
  test("greeting intenationalisation"){
    Greeting.english should be("Hi")
    Greeting.espanol should be("Hola")
  }

  test("test for reference"){
    val x = Greeting
    val y = x
    x eq y should be(true)
    val z = Greeting
    x eq z should be(true)
  }

}
