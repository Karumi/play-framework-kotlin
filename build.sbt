
name := "play-kotlin-example"

version := "1.0"

lazy val `play-kotlin-example` = (project in file(".")).enablePlugins(PlayJava)
  .settings(
    kotlinLib("stdlib"),
    kotlincOptions += "-verbose"
  )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(javaJdbc, ehcache, javaWs, guice)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")