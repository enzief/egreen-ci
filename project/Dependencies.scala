package com.round

import sbt._

object Dependencies {

  object CompilerPlugin {
    val kindProjector: ModuleID = "org.spire-math" %% "kind-projector"     % "0.9.8"
    val monadicFor:    ModuleID = "com.olegpy"     %% "better-monadic-for" % "0.2.4"
  }

  object SbtPlugin {
    val buildInfo:          ModuleID = "com.eed3si9n"          % "sbt-buildinfo"          % "0.7.0"
    val coursier:           ModuleID = "io.get-coursier"       % "sbt-coursier"           % "1.0.3"
    val dynver:             ModuleID = "com.dwijnand"          % "sbt-dynver"             % "3.0.0"
    val fs2gRPC:            ModuleID = "org.lyranthe.fs2-grpc" % "sbt-java-gen"           % "0.4.0-M1"
    val packager:           ModuleID = "com.typesafe.sbt"      % "sbt-native-packager"    % "1.3.9"
    val partialUnification: ModuleID = "org.lyranthe.sbt"      % "partial-unification"    % "1.1.2"
    val sbtHeader:          ModuleID = "de.heikoseeberger"     % "sbt-header"             % "5.0.0"
    val sbtRevolver:        ModuleID = "io.spray"              % "sbt-revolver"           % "0.9.1"
    val scalafix:           ModuleID = "ch.epfl.scala"         % "sbt-scalafix"           % "0.9.0"
    val scalafmt:           ModuleID = "com.geirsson"          % "sbt-scalafmt"           % "1.6.0-RC4"
    val scalastyle:         ModuleID = "org.scalastyle"        %% "scalastyle-sbt-plugin" % "1.0.0"
  }

  val commonsIo:      ModuleID = "commons-io"          % "commons-io" % "2.6"
  val scaluzzi:       ModuleID = "com.github.vovapolu" %% "scaluzzi"  % "0.1.1"
  val slf4j:          ModuleID = "org.slf4j"           % "slf4j-api"  % "1.7.25"
  val typesafeConfig: ModuleID = "com.typesafe"        % "config"     % "1.3.3"

  object Akka {
    val org:     String = "com.typesafe.akka"
    val version: String = "2.5.16"

    val actor:    ModuleID = org %% "akka-actor"    % version
    val protobuf: ModuleID = org %% "akka-protobuf" % version
  }

  object Cassandra {
    val org:     String = "com.datastax.cassandra"
    val version: String = "3.6.0"

    val driver: ModuleID = org % "cassandra-driver-core" % version
  }

  object Cats {
    val org:           String = "org.typelevel"
    val catsVersion:   String = "1.4.0"
    val effectVersion: String = "1.0.0"

    val core:      ModuleID = org %% "cats-core"   % catsVersion
    val effect:    ModuleID = org %% "cats-effect" % effectVersion
    val machinist: ModuleID = org %% "machinist"   % "0.6.4"
    val macros:    ModuleID = org %% "cats-macros" % catsVersion
  }

  object Eventuate {
    val version: String = "0.10"

    val core:      ModuleID = "com.rbmhtechnology" %% "eventuate-core"          % version
    val cassandra: ModuleID = "com.rbmhtechnology" %% "eventuate-log-cassandra" % version
    val leveldb:   ModuleID = "com.rbmhtechnology" %% "eventuate-log-leveldb"   % version
  }

  object Fs2 {
    val org:     String = "co.fs2"
    val version: String = "1.0.0"

    val core: ModuleID = org %% "fs2-core" % version
    val io:   ModuleID = org %% "fs2-io"   % version
  }

  object Google {
    val guava:    ModuleID = "com.google.guava"    % "guava"         % "20.0"
    val protobuf: ModuleID = "com.google.protobuf" % "protobuf-java" % "3.5.1"
  }

  object Grpc {
    val org:     String = "io.grpc"
    val version: String = "1.16.1"

    val context:      ModuleID = org % "grpc-context"       % version
    val core:         ModuleID = org % "grpc-core"          % version
    val netty:        ModuleID = org % "grpc-netty"         % version
    val protobuf:     ModuleID = org % "grpc-protobuf"      % version
    val protobufLite: ModuleID = org % "grpc-protobuf-lite" % version
    val services:     ModuleID = org % "grpc-services"      % version
    val stub:         ModuleID = org % "grpc-stub"          % version
  }

  object Monocle {
    val org:     String = "com.github.julien-truffaut"
    val version: String = "1.5.1-cats"

    val core:   ModuleID = org %% "monocle-core"  % version
    val macros: ModuleID = org %% "monocle-macro" % version
  }

  object Netty {
    val org:     String = "io.netty"
    val version: String = "4.1.30.Final"

    val codec:   ModuleID = org % "netty-codec"   % version
    val handler: ModuleID = org % "netty-handler" % version
  }

  object ScalaPB {
    val org:     String = "com.thesamet.scalapb"
    val version: String = "0.8.2"

    val grpc:    ModuleID = org %% "scalapb-runtime-grpc" % version
    val runtime: ModuleID = org %% "scalapb-runtime"      % version % "protobuf"
  }

  object Testing {
    val scalaCheck: ModuleID = "org.scalacheck" %% "scalacheck" % "1.14.0"
    val scalatest:  ModuleID = "org.scalatest"  %% "scalatest"  % "3.0.5"
  }

  object ScalazZio {
    val org:        String = "org.scalaz"
    val zioVersion: String = "0.3.1"

    val core:    ModuleID = org %% "scalaz-zio"         % zioVersion
    val interop: ModuleID = org %% "scalaz-zio-interop" % zioVersion
  }
}
