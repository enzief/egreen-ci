import com.round._
import com.round.Dependencies._

enablePlugins(ProjectPlugin)

fork in Test in ThisBuild := true

resolvers in ThisBuild ++= Seq(
  Resolver.url("enzief", url("https://raw.githubusercontent.com/enzief/publish-local/master/"))(
    Resolver.ivyStylePatterns
  ),
  Resolver.bintrayRepo("rbmhtechnology", "maven")
)

skip in publish in ThisBuild := true

lazy val grpc = (project in file("."))
  .enablePlugins(
    GcpDockerPlugin,
    ProjectPlugin,
    GrpcPlugin,
    JavaServerAppPackaging
  )
  .settings(
    name := "egreen-ci",
    Compile / unmanagedResourceDirectories += sourceDirectory.value / "main/protobuf",
    skip in publish            := false,
    publishArtifact in makePom := true,
    publishArtifact            := true,
    libraryDependencies ++= Seq(
      commonsIo,
      typesafeConfig,
      Cassandra.driver,
      Cats.core,
      Cats.effect,
      Eventuate.core,
      Eventuate.cassandra,
      Eventuate.leveldb,
      Fs2.core,
      Grpc.netty,
      Grpc.services,
      Monocle.core,
      Monocle.macros,
      ScalaPB.grpc,
      ScalaPB.runtime,
      Testing.scalaCheck,
      Testing.scalatest
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

addCommandAlias(
  "check",
  ";headerCheck;test:headerCheck" +
    ";scalafmtCheck;test:scalafmtCheck;scalafmtSbtCheck" +
    ";scalafixTest;test:scalafixTest" +
    ";evicted;test:evicted" +
    ";scalafix;test:scalafix;scalastyle;test:scalastyle"
)
