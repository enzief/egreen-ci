import com.round._
import com.round.Dependencies._
import com.round.ProjectPlugin.IntegrationTestOps

enablePlugins(ProjectPlugin)

fork in Test in ThisBuild := true

resolvers in ThisBuild ++= Seq(
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
  .enableIntegrationTests
  .settings(
    name := "egreen-ci",
    Compile / unmanagedResourceDirectories += sourceDirectory.value / "main/protobuf",
    skip in publish            := false,
    publishArtifact in makePom := true,
    publishArtifact            := true,
    libraryDependencies ++= Seq(
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
      ScalazZio.core,
      ScalazZio.interop,
      Testing.scalaCheck,
      Testing.scalatest
    )
  )

addCommandAlias(
  "fmt",
  ";scalafmtSbt;scalafmt;test:scalafmt;it:scalafmt"
)

addCommandAlias(
  "wip",
  ";headerCreate;test:headerCreate;it:headerCreate" +
  ";fmt" +
  ";test:compile;it:compile"
)

addCommandAlias(
  "check",
  ";headerCheck;test:headerCheck;it:headerCheck" +
  ";scalafmtCheck;test:scalafmtCheck;scalafmtSbtCheck;it:scalafmtCheck" +
  ";evicted;test:evicted;it:evicted" +
  ";scalafix;test:scalafix;it:scalafix"
)
