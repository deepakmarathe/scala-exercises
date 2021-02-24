package com.deepakm.scala.exercises.stdlib.caseclasses

abstract class Term
case class Var(name: String) extends Term
case class Fun(arg: String, body: Term) extends Term
case class App(f: Term, v: Term) extends Term

object CaseClasses{
  def main(args: Array[String]): Unit = {
    Fun("x", Fun("y", App(Var("x"), Var("y"))))
    val x = Var("x")
    println(x.name)
  }
}

