import sbt._
import sbt.Keys._
import org.scalafmt.sbt.ScalafmtPlugin.autoImport.{scalafmtOnCompile, scalafmtSbt}
import djx.sbt.depts.output.Djx314DeptsPlugin.autoImport.scalaV

object Settings {

  val setting1 = scalaVersion      := scalaV.v213
  val setting2 = scalafmtOnCompile := true

  val setting3 = scalaVersion      := scalaV.v3
  val setting4 = scalacOptions ++= Seq("-feature")
  val setting5 = scalacOptions ++= Seq("-feature", "-deprecation", "-Ymacro-annotations")
  val setting6 = Compile / compile := ((Compile / compile) dependsOn (Compile / scalafmtSbt)).value

  val settings       = Seq(setting1, setting2, setting5, setting6)
  val scala3Settings = Seq(setting3, setting2, setting4, setting6)

}
