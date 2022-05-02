Settings.settings
name := "a15"

javaOptions ++= List("-Xss10m")
javacOptions ++= List("-Xss10m", "-Dfile.encoding=utf-8")
scalacOptions ++= List("-Dfile.encoding=utf-8")
run / fork := true

libraryDependencies ++= Seq(
  "dev.zio"  %% "zio-logging"       % Dependencies.versions.zioLogging,
  "dev.zio"  %% "zio-logging-slf4j" % Dependencies.versions.zioLogging,
  "org.slf4j" % "slf4j-simple"      % Dependencies.versions.slef4j
)

scalacOptions ++= Seq("-feature", "-deprecation", "-Ymacro-annotations")
