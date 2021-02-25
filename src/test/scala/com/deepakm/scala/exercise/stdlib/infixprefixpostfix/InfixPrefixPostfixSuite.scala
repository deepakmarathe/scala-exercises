package com.deepakm.scala.exercise.stdlib.infixprefixpostfix

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

import scala.language.postfixOps

@RunWith(classOf[JUnitRunner])
class InfixPrefixPostfixSuite extends AnyFunSuite {
  test("Any method which takes a single parameter can be used as an infix operator: a.m(b) can also be written as a m b. ") {
    class M {
      def m(x: Int): Int = x + 1
    }
    val m = new M
    m.m(1) should be(2)
    m m 1 should be(2)

    val g: Int = 3
    (g + 4) should be(7) // + is an infix operator
    g.+(4) should be(7)
  }

  test("Infix operators do NOT work if an object has a method that takes two parameters: ") {
    val g: String = "Check out the big brains on Brad!"

    g indexOf 'o' should be(6) //indexOf(Char) can be used as an infix operator

    // g indexOf 'o', 4 should be (6) //indexOf(Char, Int) cannot be used as an infix operator

    g.indexOf('o', 7) should be(25)
  }

  test("precedence") {
    /**
     * Any method which does not require a parameter can be used as a postfix operator: a.m can be written as a m.
     *
     * For instance, a.+(b) is equivalent to a + b and a.! is the same as a!.
     *
     * Postfix operators have lower precedence than infix operators, so:
     *
     * foo bar baz means foo.bar(baz).
     * foo bar baz bam means (foo.bar(baz)).bam
     * foo bar baz bam bim means (foo.bar(baz)).bam(bim).
     */
    val g: Int = 31
    (g toHexString) should be("1f")
  }

  test("Prefix operators work if an object has a method name that starts with unary_ :") {
    val g: Int = 31
    (-g) should be(-31)
  }

  test("create our own prefix operators for the classes") {
    class Stereo {
      def unary_+ = "on"

      def unary_- = "off"
    }

    val stereo = new Stereo
    (+stereo) should be("on")
    (-stereo) should be("off")
  }
}
