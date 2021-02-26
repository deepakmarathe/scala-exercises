package com.deepakm.scala.exercise.stdlib.uniformaccessprinciple

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
class UniformAccessPrincipleSuite extends AnyFunSuite{
  test("This principle states that variables and parameterless functions should be accessed using the same syntax."){
    class Test1(val age: Int = 10)
    class Test2(_age: Int) {
      def age: Int = _age
    }

    new Test1(10).age should be(10)
    new Test2(11).age should be(11)
  }
}
