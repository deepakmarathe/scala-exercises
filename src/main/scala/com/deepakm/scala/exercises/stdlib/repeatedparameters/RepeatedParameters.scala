package com.deepakm.scala.exercises.stdlib.repeatedparameters

object RepeatedParameters {
  def repeatedParameterMethod(x: Int, y: String, z: Any*) = {
    "%d %ss can give you %s".format(x, y, z.mkString(", "))
  }

  def main(args: Array[String]): Unit = {

  }
}
