package com.deepakm.scala.exercise.cats.monad

object MonadInstances {

  import cats._

  implicit def optionMonad(implicit app: Applicative[Option]) =
    new Monad[Option] {
      // Define flatMap using Option's flatten method
      override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
        app.map(fa)(f).flatten

      // Reuse this definition from Applicative.
      override def pure[A](a: A): Option[A] = app.pure(a)

      override def tailRecM[A, B](a: A)(f: A => Option[Either[A, B]]): Option[B] = ???
    }

  def main(args: Array[String]): Unit = {

  }
}
