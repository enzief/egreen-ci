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
    Compile / PB.protoSources += baseDirectory.in(Compile).value / "../grpc-spec/src",
    Compile / unmanagedResourceDirectories += sourceDirectory.value / "main/protobuf",
    name                       := "egreen-ci",
    skip in publish            := false,
    publishArtifact in makePom := true,
    publishArtifact            := true,
    libraryDependencies ++= Seq(
      betterFiles % Test,
      enumeratum,
      typesafeConfig,
      Akka.actor,
      Cassandra.driver,
      Cats.core,
      Cats.effect,
      Cats.kernel,
      CouchBase.coreIo,
      CouchBase.javaClient,
      Eventuate.core,
      Eventuate.cassandra,
      Eventuate.leveldb,
      Fs2.core,
      Google.protobuf,
      Grpc.core,
      Grpc.netty,
      Grpc.services,
      Grpc.stub,
      Json.argonaut,
      Monocle.core,
      Monocle.macros,
      ReactiveX.rxjava,
      ScalaPB.grpc,
      ScalaPB.lenses,
      ScalaPB.runtime,
      ScalazZio.core,
      ScalazZio.interop,
      Testing.scalaCheck % Test,
      Testing.scalatest  % Test
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
  ";scalafix --check;test:scalafix --check;it:scalafix --check" +
  ";scalastyle;test:scalastyle;it:scalastyle"
)
