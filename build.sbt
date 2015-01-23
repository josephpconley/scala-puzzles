//import sbt.Keys._

//lazy val Joe = config("joe") extend(Compile)
//
//lazy val joeSettings = inConfig(Joe)(Classpaths.configSettings ++ Defaults.configTasks ++ Defaults.compileSettings ++ Seq(
//  mappings in (Joe, packageBin) ~= { (ms: Seq[(File, String)]) =>
//    ms filter {
//      case (file, toPath) => {
//        val shouldInclude = toPath.startsWith("npr")
//        println("===========" + file + "  " + toPath + " " + shouldInclude)
//        shouldInclude
//      }
//    }
//  }
//))
//
//lazy val root = (project in file(".")).configs(Joe).settings(
//  name := "puzzles",
//  version := "0.1",
//  scalaVersion := "2.10.3",
//  libraryDependencies ++= Seq(
//    "org.specs2" %% "specs2" % "2.3.4" % "test",
//    "net.sourceforge.htmlunit" % "htmlunit" % "2.13",
//    "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.2",
//    "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.2"
//  )
//).settings(joeSettings:_*)

name := "puzzles"

version := "0.1"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.3.4" % "test",
  "net.sourceforge.htmlunit" % "htmlunit" % "2.13",
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.2",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.2"
)

lazy val CustomPackage = config("custompackage") extend(Compile) describedAs("Custom package configuration")

mappings in (Compile, packageBin) ~= { (ms: Seq[(File, String)]) =>
  ms filter {
    case (file, toPath) => {
      val shouldInclude = toPath.startsWith("npr")
      println("===========" + file + "  " + toPath + " " + shouldInclude)
      shouldInclude
    }
  }
}

lazy val root = project in file(".") overrideConfigs (CustomPackage)

//packageBin in CustomPackage := {
//  val log = streams.value.log
//  import log._
//  info("""Returning custom packaging artifact, i.e. file(".")""")
//  file("npr")
//}
//
//lazy val root = project in file(".") overrideConfigs (CustomPackage)