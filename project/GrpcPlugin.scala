package com.round

import sbt._
import sbt.Keys._
import org.lyranthe.fs2_grpc.java_runtime.sbt_gen.Fs2Grpc
import org.lyranthe.fs2_grpc.java_runtime.sbt_gen.Fs2GrpcPlugin.autoImport._

object GrpcPlugin extends AutoPlugin {

  override def requires: Plugins = Fs2Grpc
  override def trigger           = allRequirements

  override val projectSettings: Seq[Def.Setting[_]] = Seq(
    scalapbCodeGeneratorOptions += CodeGeneratorOption.FlatPackage
  )
}
