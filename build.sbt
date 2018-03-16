
name := "akka-cluster"

version := "0.1"

scalaVersion := "2.12.4"

val Version = "2.5.11"

val LoggerArtifactId = "ch.qos.logback"

val LoggerVersion = "1.2.3"


libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % Version,
	"com.typesafe.akka" %% "akka-actor-typed" % Version,
	"com.typesafe.akka" %% "akka-slf4j" % Version,
	"com.typesafe.akka" %% "akka-cluster-typed" % Version,
	"com.typesafe.akka" %% "akka-distributed-data" % Version,
	"com.typesafe" % "config" % "1.3.3",
	LoggerArtifactId % "logback-core" % LoggerVersion,
	LoggerArtifactId % "logback-classic" % LoggerVersion,
	"com.typesafe.scala-logging" %% "scala-logging" % "3.7.1"
)