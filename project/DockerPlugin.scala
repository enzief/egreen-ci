package com.round

import sbt.Keys._
import sbt._

/**
  * Docker build settings biased to Google Cloud Platform.
  */
object GcpDockerPlugin extends AutoPlugin {
  import com.typesafe.sbt.SbtNativePackager._
  import com.typesafe.sbt.packager.Keys._
  import com.typesafe.sbt.packager.docker._

  override def requires: Plugins = DockerPlugin

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    dockerBaseImage := "openjdk:8-jre-alpine",
    dockerCommands := {
      val cmds = dockerCommands.value
      cmds.head +: Cmd("RUN", "apk --no-cache add bash") +: cmds.tail
    },
    dockerExposedPorts += 9000,
    dockerUpdateLatest   := true,
    Docker / maintainer  := "Yui Pham <ngocduy.pham@outlook.com>",
    Docker / packageName := name.value,
    Docker / version     := version.value.replaceAll("[\\+-]", "_")
  )
}
