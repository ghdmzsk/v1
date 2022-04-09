import sbt._
import sbt.Keys._

object Dependent {

  object versions {
    val catsEffect = "3.3.4"
    val sttp       = "3.3.18"
    val zio        = "1.0.13"
    val zioLogging = "0.5.14"
    val tapir      = "0.20.0-M5"
    val distage    = "1.0.8"
    // val http4s         = "0.23.7"
    val kindProjector = "0.13.2"
    // val macwire        = "2.5.3"
    val slf4j                     = "2.0.0-alpha6"
    val scalaTags                 = "0.11.0"
    val scalajsJQuery             = "3.2.0"
    val scalajsDom                = "2.1.0"
    val jqueryJSSource            = "3.6.0"
    val slick                     = "3.4.0-M1"
    val circe                     = "0.15.0-M1"
    val scalajsStubs              = "1.1.0"
    val zhttp                     = "1.0.0.0-RC21"
    val mysql                     = "8.0.26"
    val zioInteropReactivestreams = "1.3.9"
  }

  val catsEffect = Seq("org.typelevel" %% "cats-effect" % versions.catsEffect)
  val sttp       = Seq("com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % versions.sttp)

  val zio = Seq(
    "dev.zio" %% "zio-test"          % versions.zio % "test",
    "dev.zio" %% "zio-test-sbt"      % versions.zio % "test",
    "dev.zio" %% "zio-test-magnolia" % versions.zio % "test" // optional
  )

  val zioLogging = Seq("dev.zio" %% "zio-logging" % versions.zioLogging)

  val tapir = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-zio"                % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server"    % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs"       % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui"         % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-redoc"              % versions.tapir
  )

  val tapirSttp = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-client" % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe"  % versions.tapir
  )

  val distage = Seq("io.7mind.izumi" %% "distage-core" % versions.distage)

  /*val http4s = Seq(
    "org.http4s" %% "http4s-dsl"          % versions.http4s,
    "org.http4s" %% "http4s-blaze-server" % versions.http4s,
    "org.http4s" %% "http4s-blaze-client" % versions.http4s,
    "org.http4s" %% "http4s-circe"        % versions.http4s
  )*/

  /*val macwire = Seq(
    "com.softwaremill.macwire" %% "macros"     % versions.macwire % "provided",
    "com.softwaremill.macwire" %% "macrosakka" % versions.macwire % "provided",
    "com.softwaremill.macwire" %% "util"       % versions.macwire,
    "com.softwaremill.macwire" %% "proxy"      % versions.macwire
  )*/

  val kindProjector = "org.typelevel" % "kind-projector" % versions.kindProjector cross CrossVersion.full

  val slf4j = Seq("org.slf4j" % "slf4j-simple" % versions.slf4j)

  val scalaTags      = Seq("com.lihaoyi" %% "scalatags" % versions.scalaTags)
  val jqueryJSSource = Seq("org.webjars" % "jquery" % versions.jqueryJSSource)
  val mysql          = Seq("mysql" % "mysql-connector-java" % versions.mysql)

  val slick = Seq(
    "com.typesafe.slick" %% "slick"          % versions.slick,
    "com.typesafe.slick" %% "slick-codegen"  % versions.slick,
    "com.typesafe.slick" %% "slick-hikaricp" % versions.slick
  )

  val circe = Seq(
    "io.circe" %% "circe-core"    % versions.circe,
    "io.circe" %% "circe-generic" % versions.circe,
    "io.circe" %% "circe-parser"  % versions.circe
  )

  val scalajsStubs = Seq("org.scala-js" %% "scalajs-stubs" % versions.scalajsStubs)

  val zhttp = Seq("io.d11" %% "zhttp" % versions.zhttp, "io.d11" %% "zhttp-test" % versions.zhttp % Test)

  val zioInteropReactivestreams = "dev.zio" %% "zio-interop-reactivestreams" % versions.zioInteropReactivestreams

}
