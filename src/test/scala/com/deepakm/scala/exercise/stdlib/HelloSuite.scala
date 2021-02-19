package com.deepakm.scala.exercise.stdlib

import com.deepakm.scala.exercises.Hello
import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HelloSuite extends AnyFunSuite {
  test("hello() is always true") {
    def hello = new Hello()
    assert(hello.hello())
  }
}