package com.round

import sbt._

object Dependencies {

  object CompilerPlugin {
    val kindProjector: ModuleID = "org.spire-math" %% "kind-projector"     % "0.9.9"
    val monadicFor:    ModuleID = "com.olegpy"     %% "better-monadic-for" % "0.3.0-M4"
  }

  object SbtPlugin {
    val buildInfo:          ModuleID = "com.eed3si9n"      % "sbt-buildinfo"             % "0.9.0"
    val coursier:           ModuleID = "io.get-coursier"   % "sbt-coursier"              % "1.1.0-M9"
    val dynver:             ModuleID = "com.dwijnand"      % "sbt-dynver"                % "3.1.0"
    val explicitDeps:       ModuleID = "com.github.cb372"  % "sbt-explicit-dependencies" % "0.2.8"
    val packager:           ModuleID = "com.typesafe.sbt"  % "sbt-native-packager"       % "1.3.15"
    val partialUnification: ModuleID = "org.lyranthe.sbt"  % "partial-unification"       % "1.1.2"
    val sbtHeader:          ModuleID = "de.heikoseeberger" % "sbt-header"                % "5.1.0"
    val scalafix:           ModuleID = "ch.epfl.scala"     % "sbt-scalafix"              % "0.9.3"
    val scalafmt:           ModuleID = "com.geirsson"      % "sbt-scalafmt"              % "1.6.0-RC4"
    val scalastyle:         ModuleID = "org.scalastyle"    %% "scalastyle-sbt-plugin"    % "1.0.0"
  }

  val bcrypt:         ModuleID = "com.github.t3hnar"    %% "scala-bcrypt" % "3.1"
  val betterFiles:    ModuleID = "com.github.pathikrit" %% "better-files" % "3.7.0"
  val enumeratum:     ModuleID = "com.beachape"         %% "enumeratum"   % "1.5.13"
  val scaluzzi:       ModuleID = "com.github.vovapolu"  %% "scaluzzi"     % "0.1.1"
  val slf4j:          ModuleID = "org.slf4j"            % "slf4j-api"     % "1.7.25"
  val typesafeConfig: ModuleID = "com.typesafe"         % "config"        % "1.3.3"

  object Akka {
    val org:     String = "com.typesafe.akka"
    val version: String = "2.5.20"

    val actor:    ModuleID = org %% "akka-actor"    % version
    val protobuf: ModuleID = org %% "akka-protobuf" % version
    val remote:   ModuleID = org %% "akka-remote"   % version
    val stream:   ModuleID = org %% "akka-stream"   % version
  }

  object Cassandra {
    val org:     String = "com.datastax.cassandra"
    val version: String = "3.6.0"

    val driver: ModuleID = org % "cassandra-driver-core" % version
  }

  object Cats {
    val org:           String = "org.typelevel"
    val catsVersion:   String = "1.6.0"
    val effectVersion: String = "1.2.0"

    val core:      ModuleID = org %% "cats-core"   % catsVersion
    val effect:    ModuleID = org %% "cats-effect" % effectVersion
    val kernel:    ModuleID = org %% "cats-kernel" % catsVersion
    val machinist: ModuleID = org %% "machinist"   % "0.6.6"
    val macros:    ModuleID = org %% "cats-macros" % catsVersion
  }

  object CouchBase {
    val org:     String = "com.couchbase.client"
    val version: String = "2.7.2"

    val coreIo:     ModuleID = org % "core-io"     % "1.7.2"
    val javaClient: ModuleID = org % "java-client" % version
  }

  object Eventuate {
    val version: String = "0.10"

    val core:      ModuleID = "com.rbmhtechnology" %% "eventuate-core"          % version
    val cassandra: ModuleID = "com.rbmhtechnology" %% "eventuate-log-cassandra" % version
    val leveldb:   ModuleID = "com.rbmhtechnology" %% "eventuate-log-leveldb"   % version
  }

  object Fs2 {
    val org:     String = "co.fs2"
    val version: String = "1.0.3"

    val core: ModuleID = org %% "fs2-core" % version
    val io:   ModuleID = org %% "fs2-io"   % version
  }

  object Fs2gRPC {
    val org:     String = "org.lyranthe.fs2-grpc"
    val version: String = "0.4.0-M4"

    val plugin:  ModuleID = org % "sbt-java-gen"  % version
    val runtime: ModuleID = org %% "java-runtime" % version
  }

  object Google {
    val guava:    ModuleID = "com.google.guava"    % "guava"         % "27.0.1-jre"
    val protobuf: ModuleID = "com.google.protobuf" % "protobuf-java" % "3.6.1"
  }

  object Grpc {
    val org:     String = "io.grpc"
    val version: String = "1.18.0"

    val context:      ModuleID = org % "grpc-context"       % version
    val core:         ModuleID = org % "grpc-core"          % version
    val netty:        ModuleID = org % "grpc-netty"         % version
    val protobuf:     ModuleID = org % "grpc-protobuf"      % version
    val protobufLite: ModuleID = org % "grpc-protobuf-lite" % version
    val services:     ModuleID = org % "grpc-services"      % version
    val stub:         ModuleID = org % "grpc-stub"          % version
  }

  object Json {
    val argonaut: ModuleID = "io.argonaut" %% "argonaut" % "6.2.2"
  }

  object Monocle {
    val org:     String = "com.github.julien-truffaut"
    val version: String = "1.6.0-M1"

    val core:   ModuleID = org %% "monocle-core"  % version
    val macros: ModuleID = org %% "monocle-macro" % version
  }

  object Netty {
    val org:     String = "io.netty"
    val version: String = "4.1.32.Final"

    val codec:     ModuleID = org % "netty-codec"     % version
    val handler:   ModuleID = org % "netty-handler"   % version
    val transport: ModuleID = org % "netty-transport" % version
  }

  object ReactiveX {
    val org:     String = "io.reactivex"
    val version: String = "1.3.8"

    val rxjava: ModuleID = org % "rxjava" % version
  }

  object ScalaPB {
    val org:     String = "com.thesamet.scalapb"
    val version: String = "0.8.4"

    val grpc:    ModuleID = org %% "scalapb-runtime-grpc" % version
    val lenses:  ModuleID = org %% "lenses"               % version
    val runtime: ModuleID = org %% "scalapb-runtime"      % version % "protobuf"
  }

  object Scalaz {
    val org:     String = "org.scalaz"
    val version: String = "7.2.27"

    val core: ModuleID = org %% "scalaz-core" % version
  }

  object ScalazZio {
    val org:        String = Scalaz.org
    val zioVersion: String = "0.6.0"

    val core:    ModuleID = org %% "scalaz-zio"              % zioVersion
    val interop: ModuleID = org %% "scalaz-zio-interop-cats" % zioVersion
  }

  object Testing {
    val scalaCheck: ModuleID = "org.scalacheck" %% "scalacheck" % "1.14.0"
    val scalatest:  ModuleID = "org.scalatest"  %% "scalatest"  % "3.0.5"
  }
}
