logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.11")
//You need to download pfn/kotlin-plugin project and run "sbt scripted to get the 1.0.9-SNAPSHOT version"
addSbtPlugin("com.hanhuy.sbt" % "kotlin-plugin" % "1.0.9-SNAPSHOT")
addSbtPlugin("com.karumi" % "ktlint-sbt" % "0.0.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "4.1.0")
