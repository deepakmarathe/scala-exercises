package com.deepakm

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HelloSuite extends AnyFunSuite {
  test("hello() is always true") {
    def hello = new Hello()
    assert(hello.hello())
  }
}