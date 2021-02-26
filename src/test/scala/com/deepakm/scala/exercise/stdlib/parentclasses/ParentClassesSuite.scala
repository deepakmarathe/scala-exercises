package com.deepakm.scala.exercise.stdlib.parentclasses

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ParentClassesSuite extends AnyFunSuite {
  test("Class hierarchy is linear, a class can only extend from one parent class: ") {
    class Soldier(val firstName: String, val lastName: String) {}
    class Pilot(override val firstName: String, override val lastName: String, val squadron: Long)
      extends Soldier(firstName, lastName)
    val pilot = new Pilot("John", "Yossarian", 256)
    pilot.firstName should be("John")
    pilot.lastName should be("Yossarian")
  }

  test("An abstract class, as in Java, cannot be instantiated and only inherited:") {
    abstract class Soldier(val firstName: String, val lastName: String) {}

    // if you uncomment this line, it will fail compilation
    //    val soldier = new Soldier
  }

  test("A class can be placed inside an abstract class just like in Java: "){
    abstract class Soldier(val firstName: String, val lastName: String) {

      class Catch(val number: Long) {
        // nothing to do here.  Just observe that it compiles
      }

    }
    class Pilot(override val firstName: String, override val lastName: String, val squadron: Long)
      extends Soldier(firstName, lastName)

    val pilot = new Pilot("John", "Yossarian", 256)
    val catchNo = new pilot.Catch(22) //using the pilot instance's path, create an catch object for it.
    catchNo.number should be(22)
  }
}
