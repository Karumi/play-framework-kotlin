logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.11")
addSbtPlugin("com.hanhuy.sbt" % "kotlin-plugin" % "1.0.8")
addSbtPlugin("com.karumi" % "ktlint-sbt" % "0.0.1")