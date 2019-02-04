package com.round

import com.round.Dependencies._
import sbt._

/**
  * Used to manage library evictions
  */
object DependencyOverrides {

  val settings: Seq[ModuleID] = Seq(
    typesafeConfig,
    slf4j,
    Akka.actor,
    Akka.protobuf,
    Akka.remote,
    Akka.stream,
    Cassandra.driver,
    Cats.core,
    Cats.effect,
    Cats.machinist,
    Cats.macros,
    Fs2.core,
    Fs2.io,
    Google.guava,
    Google.protobuf,
    Grpc.core,
    Grpc.netty,
    Grpc.protobuf,
    Grpc.services,
    Grpc.stub,
    Netty.codec,
    Netty.handler,
    Netty.transport,
    ScalaPB.grpc,
    ScalaPB.runtime,
    Scalaz.core
  )
}
