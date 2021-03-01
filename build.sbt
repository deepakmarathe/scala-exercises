
name := "scala-exercises"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "org.scala-exercises" %% "exercise-compiler" % "0.6.7",
  "org.scala-exercises" %% "definitions" % "0.6.7",
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.scalatest" %% "scalatest" % "3.2.4",
  "org.scalacheck" %% "scalacheck" % "1.15.3",
  "org.scalatestplus" %% "scalacheck-1-14" % "3.2.2.0",
  "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.5",
  "org.scalatest" %% "scalatest" % "3.1.1",
  "org.typelevel" %% "cats-core_2.11" % "0.4.1",
  "com.chuusai" %% "shapeless" % "2.3.3"
)

scalacOptions += "-Ypartial-unification"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.1"

mainClass in Compile := Some("com.deepakm.scala.exercises.Hello")
//mainClass in Compile := Some("com.deepakm.scala.exercise.stdlib.stdlib.Asserts")

resolvers += "cats-core" at "https://mvnrepository.com/artifact/org.typelevel/cats-core" // it gives the same errors without this line

scalacOptions ++= Seq("-Xfatal-warnings", "-Ypartial-unification")

val exportFullResolvers = taskKey[Unit]("debug resolvers")

exportFullResolvers := {
  for {
    (resolver, idx) <- fullResolvers.value.zipWithIndex
  } println(s"${idx}.  ${resolver.name}")
}

