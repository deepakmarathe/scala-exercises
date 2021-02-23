package com.deepakm.scala.exercise.stdlib.`object`

import com.deepakm.scala.exercises.stdlib.`object`.Person
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PersonSuite extends AnyFunSuite {
  test("access modifier test") {
    val clark = new Person("Clark Kent", "Superman")
    val peter = new Person("Peter Parker", "Spider-Man")

    Person.showMeInnerSecret(clark) should be("Superman")
    Person.showMeInnerSecret(peter) should be("Spider-Man")
  }
}
