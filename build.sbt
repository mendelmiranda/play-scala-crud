name := """play-scala-crud"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test,
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.webjars" % "angularjs" % "1.3.15",
  "org.webjars" % "angular-ui-bootstrap" % "0.13.0",
  "org.webjars" %% "webjars-play" % "2.3.0",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "com.typesafe.play" %% "anorm" % "2.4.0",
  evolutions)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
