// Copyright (c) 2018 Round, Inc.. All rights reserved.

package com.round.egreen

import java.util.UUID

import cats.data._
import cats.effect.{Effect, IO}
import com.redis.RedisClientPool
import com.typesafe.config.{Config, ConfigFactory}
import fs2.{async, Stream, StreamApp}
import fs2.async.mutable.Queue
import io.circe.Json
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import org.bson.codecs.configuration.CodecRegistries._
import org.bson.codecs.configuration.CodecRegistry
import org.http4s._
import org.http4s.server.AuthMiddleware
import org.http4s.EntityDecoder
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeBuilder
import org.mongodb.scala._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import pdi.jwt.JwtAlgorithm
import pdi.jwt.algorithms.JwtHmacAlgorithm

import scala.concurrent.ExecutionContext

object EGreenDummy {

  val ioEffect: Effect[IO]                   = null
  val stream: Stream[IO, StreamApp.ExitCode] = ???
  val config: Config                         = ConfigFactory.load()
  implicit val ec: ExecutionContext =
    scala.concurrent.ExecutionContext.Implicits.global
  BlazeBuilder[IO]
    .bindHttp(config.getInt("http.port"), "0.0.0.0")
    .serve

  val uuid = UUID.randomUUID()

  implicit def decoder: EntityDecoder[IO, String] = jsonOf[IO, String]

  val mongoClient: MongoClient = ???

  val codecRegistry: CodecRegistry = fromRegistries(
    fromProviders(classOf[UserClaim]),
    DEFAULT_CODEC_REGISTRY
  )

  val client: RedisClientPool = ???

  def apply[F[_]: Effect](
      service: AuthedService[UserClaim, F]
  ): HttpService[F] =
    AuthMiddleware(authUser).apply(service)

  private def authUser[F[_]: Effect]: Kleisli[OptionT[F, ?], Request[F], UserClaim] =
    ???

  val algorithm: JwtHmacAlgorithm = JwtAlgorithm.HS256
}

final case class UserClaim(id: UUID)

object CommandHttp extends Http4sDsl[IO] {

  val service: HttpService[IO] = EGreenDummy {
    AuthedService[UserClaim, IO] {
      case (POST -> Root) as sender =>
        parse("")
        BadRequest(sender.asJson)
    }
  }

  def parseCommand: EitherT[IO, String, Json] =
    EitherT.fromEither[IO](??? : Either[String, Json])
}

final class MongoEventRepository[F[_]] {
  import scala.concurrent.ExecutionContext.Implicits.global

  def eventStream(implicit F: Effect[F]): Stream[F, String] =
    for {
      queue    <- Stream.eval(async.boundedQueue[F, Either[Throwable, String]](1))
      envelope <- queue.dequeue.takeWhile(_.isRight).rethrow
    } yield envelope
}

class EventObserver[F[_]](queue: Queue[F, String]) extends Observer[String] {

  override def onSubscribe(subscription: Subscription): Unit = {}
  override def onNext(result: String): Unit                  = {}
  override def onError(e: Throwable): Unit                   = {}
  override def onComplete(): Unit                            = {}
}
