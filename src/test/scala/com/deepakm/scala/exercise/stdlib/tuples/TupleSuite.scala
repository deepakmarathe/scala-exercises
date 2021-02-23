package com.deepakm.scala.exercise.stdlib.tuples

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

import java.util.Date

@RunWith(classOf[JUnitRunner])
class TupleSuite extends AnyFunSuite{
  test("tuple is 1 indexed"){
    val tuple = ("apple", "dog")
    val fruit = tuple._1
    val animal = tuple._2

    fruit should be("apple")
    animal should be("dog")
  }

  test("tuple may be of mixed type"){
    val tuple5 = ("a", 1, 2.2, new Date(), "five")
    tuple5._2 should be(1)
    tuple5._5 should be("five")
  }

  test("destructuring, assigning multiple variables"){
    val student = ("Sean Rogers", 21, 3.5)
    val (name, age, gpa) = student

    name should be ("Sean Rogers")
    age should be (21)
    gpa should be (3.5)
  }

  test("swap can be used to swap the elements of a tuple"){
    val tuple = ("apple", 3).swap
    tuple._1 should be(3)
    tuple._2 should be("apple")
  }
}
