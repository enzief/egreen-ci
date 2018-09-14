package com.round

import com.round.Dependencies._
import sbt._

/**
  * Used to manage library evictions
  */
object DependencyOverrides {

  val settings: Seq[ModuleID] = Seq(
    commonsIo,
    typesafeConfig,
    slf4j,
    Akka.actor,
    Akka.protobuf,
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
    Grpc.protobuf,
    Grpc.stub,
    Netty.codec,
    Netty.handler,
    ScalaPB.grpc,
    ScalaPB.runtime
  )
}
