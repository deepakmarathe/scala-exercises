package com.deepakm.scala.exercises.stdlib.traits

trait Similarity {
  def isSimilar(x: Any): Boolean

  def isNotSimilar(x: Any): Boolean = !isSimilar(x)
}
