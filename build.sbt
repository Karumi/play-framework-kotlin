resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.12.3"
name := "play-kotlin-example"
version := "1.0"

lazy val `play-kotlin-example` = (project in file(".")).enablePlugins(PlayJava, KtlintPlugin, PlayEbean)
  .settings(
      kotlinLib("stdlib"),
      kotlincOptions += "-verbose"
)

ktlintVersion := "0.26.0"
ktlintSource := new File("lib")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

javaOptions in IntegrationTest += "-Dconfig.file=conf/application.test.conf"
javaOptions in Test += "-Dconfig.file=conf/application.test.conf"
testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v", "-a")


libraryDependencies ++= Seq(javaJdbc, ehcache, javaWs, guice,
  "io.arrow-kt" % "arrow-core" % "0.7.2",
  "io.arrow-kt" % "arrow-syntax" % "0.7.2",
  "io.arrow-kt" % "arrow-instances-core" % "0.7.2",
  "com.fasterxml.jackson.module" % "jackson-module-kotlin" % "2.9.5",
  "org.postgresql" % "postgresql" % "42.2.1",
  "com.h2database" % "h2" % "1.4.192" % Test
)
