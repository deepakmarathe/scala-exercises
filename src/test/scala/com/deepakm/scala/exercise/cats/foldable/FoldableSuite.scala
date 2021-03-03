package com.deepakm.scala.exercise.cats.foldable

import cats.{Foldable, Later, Now}
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FoldableSuite extends AnyFunSuite {
  test(" foldLeft\n\nfoldLeft is an eager left-associative fold on F using the given function. ") {
    Foldable[List].foldLeft(List(1, 2, 3), 0)(_ + _) should be(6)
    Foldable[List].foldLeft(List("a", "b", "c"), "")(_ + _) should be("abc")
  }

  test(" foldRight\n\nfoldRight is a lazy right-associative fold on F using the given function. The function has the signature (A, Eval[B]) => Eval[B] to support laziness in a stack-safe way. ") {
    val lazyResult =
      Foldable[List].foldRight(List(1, 2, 3), Now(0))((x, rest) => Later(x + rest.value))
    lazyResult.value should be(6)
  }

  test(" fold\n\nfold, also called combineAll, combines every value in the foldable using the given Monoid instance. ") {
    Foldable[List].fold(List("a", "b", "c")) should be("abc")
    Foldable[List].fold(List(1, 2, 3)) should be(6)
  }

  test(" foldMap\n\nfoldMap is similar to fold but maps every A value into B and then combines them using the given Monoid[B] instance.\n") {
    Foldable[List].foldMap(List("a", "b", "c"))(_.length) should be(3)
    Foldable[List].foldMap(List(1, 2, 3))(_.toString) should be("123")
  }

  test(" foldK\n\nfoldK is similar to fold but combines every value in the foldable using the given MonoidK[G] instance instead of Monoid[G]. ") {
    Foldable[List].foldK(List(List(1, 2), List(3, 4, 5))) should be(List(1, 2, 3, 4, 5))
    Foldable[List].foldK(List(None, Option("two"), Option("three"))) should be(Option("two"))
  }

  test(" find\n\nfind searches for the first element matching the predicate, if one exists. ") {
    Foldable[List].find(List(1, 2, 3))(_ > 2) should be(Some(3))
    Foldable[List].find(List(1, 2, 3))(_ > 5) should be(None)
  }

  test(" exists\n\nexists checks whether at least one element satisfies the predicate. ") {
    Foldable[List].exists(List(1, 2, 3))(_ > 2) should be(true)
    Foldable[List].exists(List(1, 2, 3))(_ > 5) should be(false)
  }

  test(" forall\n\nforall checks whether all elements satisfy the predicate. ") {
    Foldable[List].forall(List(1, 2, 3))(_ <= 3) should be(true)
    Foldable[List].forall(List(1, 2, 3))(_ < 3) should be(false)
  }

  test(" toList\n\nConvert F[A] to List[A]. ") {
    Foldable[List].toList(List(1, 2, 3)) should be(List(1, 2, 3))
    Foldable[Option].toList(Option(42)) should be(List(42))
    Foldable[Option].toList(None) should be(Nil)
  }

  test(" filter_\n\nConvert F[A] to List[A] only including the elements that match a predicate. ") {
    Foldable[List].filter_(List(1, 2, 3))(_ < 3) should be(List(1, 2))
    Foldable[Option].filter_(Option(42))(_ != 42) should be(Nil)
    Foldable[Option].filter_(Option(42))(_ != 42) should be(List())
  }

  test(" traverse_\n\ntraverse the foldable mapping A values to G[B], and combining them using Applicative[G] and discarding the results.\n\nThis method is primarily useful when G[_] represents an action or effect, and the specific B aspect of G[B] is not otherwise needed. The B will be discarded and Unit returned instead. ") {
    import cats.implicits._

    def parseInt(s: String): Option[Int] =
      Either.catchOnly[NumberFormatException](s.toInt).toOption

    Foldable[List].traverse_(List("1", "2", "3"))(parseInt) should be(Some(()))
    Foldable[List].traverse_(List("a", "b", "c"))(parseInt) should be(None)
  }

  test(" compose\n\nWe can compose Foldable[F[_]] and Foldable[G[_]] instances to obtain Foldable[F[G]].\n"){
    val FoldableListOption = Foldable[List].compose[Option]
    FoldableListOption.fold(List(Option(1), Option(2), Option(3), Option(4))) should be(10)
    FoldableListOption.fold(List(Option("1"), Option("2"), None, Option("3"))) should be("123")
  }

  test(" More Foldable methods : There are a few more methods that we haven't talked about but you probably can guess what they do: "){
    Foldable[List].isEmpty(List(1, 2, 3)) should be(false)
    Foldable[List].dropWhile_(List(1, 2, 3))(_ < 2) should be(List(2,3))
    Foldable[List].takeWhile_(List(1, 2, 3))(_ < 2) should be(List(1))
  }
}
