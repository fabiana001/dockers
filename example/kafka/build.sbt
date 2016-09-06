lazy val commons = Seq(
  organization := "it.cgnal",
  name := "kafka",
  version := "1.0.0",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq("-target:jvm-1.8", "-feature"),
  resolvers ++= Seq(
    Resolver.sonatypeRepo("public"),
    Resolver.typesafeRepo("releases"),
    "spray repo" at "http://repo.spray.io"
    //,Resolver.url("confluent", url("http://packages.confluent.io/maven/"))
  )
)

lazy val root = (project in file("."))
  .settings(commons: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.specs2"    %% "specs2"    % "3.7",
      "org.apache.kafka" % "kafka-clients" % "0.9.0.1",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.2",
      "org.json4s" %% "json4s-jackson" % "3.4.0"

    )
  )