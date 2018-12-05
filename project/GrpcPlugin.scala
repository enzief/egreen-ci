package com.round

import org.lyranthe.fs2_grpc.java_runtime.sbt_gen.Fs2Grpc
import org.lyranthe.fs2_grpc.java_runtime.sbt_gen.Fs2GrpcPlugin.autoImport._
import sbt._
import sbt.Keys._
import sbtprotoc.ProtocPlugin
import sbtprotoc.ProtocPlugin.autoImport.PB

object GrpcPlugin extends AutoPlugin {

  override def requires: Plugins = Fs2Grpc
  override def trigger = allRequirements

  import CodeGeneratorOption._
  override def projectSettings: Seq[Def.Setting[_]] =
    Project.inConfig(Test)(ProtocPlugin.protobufConfigSettings) ++
    Seq(
      Compile / excludeFilter := HiddenFileFilter || scalapbFilter,
      Test / PB.protoSources  := Seq(sourceDirectory.value / "test/protobuf"),
      Test / PB.targets       := Seq(scalapb.gen() -> sourceManaged.in(Test).value),
      scalapbCodeGeneratorOptions += FlatPackage
    )

  private def scalapbFilter: io.FileFilter = {
    val r = "scalapb".r
    new io.SimpleFileFilter(
      f => r.findFirstIn(f.getCanonicalPath).nonEmpty
    )
  }
}
