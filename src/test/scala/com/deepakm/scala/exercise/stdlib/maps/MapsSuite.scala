package com.deepakm.scala.exercise.stdlib.maps

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

@RunWith(classOf[JUnitRunner])
class MapsSuite extends AnyFunSuite {
  test("Maps can be created easily: ") {
    val myMap =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    myMap.size should be(4)
  }

  test("Maps do not contain multiple identical pairs: ") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")
    myMap.size should be(3)
  }

  test("Maps can be added to easily: ") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")
    val aNewMap = myMap + ("IL" -> "Illinois")
    aNewMap.contains("IL") should be(true)
  }

  test("Map values can be iterated: ") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")

    val mapValues = myMap.values
    mapValues.size should be(3)
    mapValues.head should be("Michigan") //Failed presumption: The order in maps is not guaranteed

    val lastElement = mapValues.last
    lastElement should be("Wisconsin")
  }

  test("Maps may be accessed: ") {
    val myMap =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    myMap("MI") should be("Michigan")
    myMap("IA") should be("Iowa")
  }

  test("Maps insertion with duplicate key updates previous entry with subsequent value: ") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Meechigan")
    val mapValues = myMap.values
    mapValues.size should be(3)
    myMap("MI") should be("Meechigan")
  }

  test("Map keys may be of mixed type: ") {
    val myMap = Map("Ann Arbor" -> "MI", 49931 -> "MI")
    myMap("Ann Arbor") should be("MI")
    myMap(49931) should be("MI")
  }

  test("lookup non-existent keys throws NoSuchElementException") {
    val myMap =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    intercept[NoSuchElementException] {
      myMap("TX")
    }
    myMap.getOrElse("TX", "missing data") should be("missing data")

    val myMap2 =
      Map(
        "MI" -> "Michigan",
        "OH" -> "Ohio",
        "WI" -> "Wisconsin",
        "IA" -> "Iowa") withDefaultValue "missing data"
    myMap2("TX") should be("missing data")
  }

  test("Map elements can be removed easily: ") {
    val myMap =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap - "MI"
    aNewMap.contains("MI") should be(false)
    myMap.contains("MI") should be(true)
  }

  test("Map elements can be removed in multiple: ") {
    val myMap =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap -- List("MI", "OH")

    aNewMap.contains("MI") should be(false)
    myMap.contains("MI") should be(true)

    aNewMap.contains("WI") should be(true)
    aNewMap.size should be(2)
    myMap.size should be(4)
  }

  test("Attempted removal of nonexistent elements from a map is handled gracefully: "){
    val myMap =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap - "MN"

    aNewMap.equals(myMap) should be(true)
  }

  test("Map equivalency is independent of order: "){
    val myMap1 =
      Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val myMap2 =
      Map("WI" -> "Wisconsin", "MI" -> "Michigan", "IA" -> "Iowa", "OH" -> "Ohio")

    myMap1.equals(myMap2) should be(true)
  }
}
