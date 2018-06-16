import com.round.Dependencies._

lazy val root = (project in file("."))
  .enablePlugins(com.round.ProjectPlugin)
  .enablePlugins(com.round.ProtobufPlugin)
  .enablePlugins(com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging)
  .settings(
    name := "egreen-ci",
    libraryDependencies ++= Seq(
      Cats.core,
      Cats.effect,
      Circe.core,
      Circe.literal,
      Circe.parser,
      Crypto.jwtCirce,
      Http4s.blaze,
      Http4s.circe,
      Http4s.dsl,
      DB.redis,
      DB.mongodb,
      ScalaPB.runtime,
      typesafeConfig,
      Scalatest.core % "test",
      Logback.classic
    )
  )

addCommandAlias(
  "fmt",
  ";scalafmtSbt;scalafmt;test:scalafmt"
)

// while you're working, try putting "~wip" into your sbt console
// ...but be prepared to let IntelliJ force you to reload!
addCommandAlias(
  "wip",
  ";headerCreate;test:headerCreate;fmt;test:compile"
)
