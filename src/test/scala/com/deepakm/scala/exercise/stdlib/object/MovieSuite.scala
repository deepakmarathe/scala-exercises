package com.deepakm.scala.exercise.stdlib.`object`

import com.deepakm.scala.exercises.stdlib.`object`.Movie
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

@RunWith(classOf[JUnitRunner])
class MovieSuite extends AnyFunSuite{
  test("movie name correctness test"){
    Movie.academyAwardBestMoviesForYear(1932).get.name should be("Grand Hotel")
  }
}
