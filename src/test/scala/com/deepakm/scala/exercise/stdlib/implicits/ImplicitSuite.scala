package com.deepakm.scala.exercise.stdlib.implicits

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ImplicitSuite extends AnyFunSuite {
  test("implicits") {
    class KoanIntWrapper(val original: Int) {
      def isOdd = original % 2 != 0
    }

    implicit def thisMethodNameIsIrrelevant(value: Int) =
      new KoanIntWrapper(value)

    19.isOdd should be(true)
    20.isOdd should be(false)
  }

  test("Implicits rules can be imported into your scope with an import: ") {
    object MyPredef {

      class KoanIntWrapper(val original: Int) {
        def isOdd = original % 2 != 0

        def isEven = !isOdd
      }

      implicit def thisMethodNameIsIrrelevant(value: Int) =
        new KoanIntWrapper(value)
    }

    import MyPredef._
    //imported implicits come into effect within this scope
    19.isOdd should be(true)
    20.isOdd should be(false)
    20.isEven should be(true)
  }

  test("Implicits can be used to automatically convert a value's type to another: ") {
    import java.math.BigInteger
    implicit def Int2BigIntegerConvert(value: Int): BigInteger =
      new BigInteger(value.toString)

    def add(a: BigInteger, b: BigInteger) = a.add(b)

    add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6)) == Int2BigIntegerConvert(9) should be(true)

    add(3, 6) == 9 should be(false)
    add(3, 6) == Int2BigIntegerConvert(9) should be(true)

    add(3, 6) == (9: BigInteger) should be(true)
    add(3, 6).intValue == 9 should be(true)
  }

  test("implicit function parameters") {
    //    Implicits can be used to declare a value to be provided as a default
    //    as long as an implicit value is set with in the scope.
    //    These are called Implicit Function Parameters:
    def howMuchCanIMake_?(hours: Int)(implicit dollarsPerHour: BigDecimal) =
      dollarsPerHour * hours

    implicit val hourlyRate = BigDecimal(34)

    howMuchCanIMake_?(30) should be(BigDecimal(34) * 30)
    howMuchCanIMake_?(30) should be(BigDecimal(34 * 30))
  }

  test("Implicit Function Parameters can contain a list of implicits: ") {
    def howMuchCanIMake_?(hours: Int)(implicit amount: BigDecimal, currencyName: String) =
      (amount * hours).toString() + " " + currencyName

    implicit val hourlyRate = BigDecimal(34)
    implicit val currencyName = "Dollars"

    howMuchCanIMake_?(30) should be(BigDecimal(34 * 30) + " Dollars")
  }

  test("Default arguments, though, are preferred to Implicit Function Parameters: ") {
    def howMuchCanIMake_?(hours: Int, amount: BigDecimal = 34, currencyName: String = "Dollars") =
      (amount * hours).toString() + " " + currencyName

    howMuchCanIMake_?(30) should be(30 * 34 + " Dollars")

    howMuchCanIMake_?(30, 95) should be(30 * 95 + " Dollars")
  }
}
