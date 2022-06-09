Settings.settings
name := "a08"

javaOptions += "-Xss180k"
// run / fork := true

libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.6.19"
libraryDependencies += "org.slf4j"          % "slf4j-simple"     % "1.7.36"
