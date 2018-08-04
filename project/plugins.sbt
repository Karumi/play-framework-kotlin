logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.11")
addSbtPlugin("com.hanhuy.sbt" % "kotlin-plugin" % "1.0.9")
addSbtPlugin("com.karumi" % "ktlint-sbt" % "0.0.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "4.1.0")
