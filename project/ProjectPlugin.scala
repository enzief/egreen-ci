package com.round

import scala.sys.process._
import scala.util._

import de.heikoseeberger.sbtheader.FileType
import de.heikoseeberger.sbtheader.HeaderPlugin, HeaderPlugin.autoImport._
import sbt._
import sbt.Keys._
import sbtbuildinfo.BuildInfoPlugin, BuildInfoPlugin.autoImport._
import sbtdynver.DynVerPlugin
import scalafix.sbt.ScalafixPlugin.autoImport._

object ProjectPlugin extends AutoPlugin {

  override def requires: Plugins =
    BuildInfoPlugin && HeaderPlugin && DynVerPlugin

  override def trigger = allRequirements

  override val buildSettings: Seq[Def.Setting[_]] = Seq(
    scalafixDependencies += Dependencies.scaluzzi,
    organization := "com.round",
    scalaVersion := "2.12.7"
  )

  override val projectSettings: Seq[Def.Setting[_]] =
    buildInfoSettings ++
    headerSettings ++
    Seq(
      conflictManager           := ConflictManager.strict,
      dependencyOverrides       := DependencyOverrides.settings,
      autoAPIMappings in Global := true,
      addCompilerPlugin(Dependencies.CompilerPlugin.kindProjector),
      addCompilerPlugin(Dependencies.CompilerPlugin.monadicFor),
      addCompilerPlugin(scalafixSemanticdb("4.1.0")),
      scalacOptions ++= commonScalacOptions ++ scalacOptionsFor212
    )

  private lazy val buildInfoSettings: Seq[Def.Setting[_]] = Seq(
    buildInfoKeys := Seq[BuildInfoKey](
      name,
      version,
      scalaVersion,
      sbtVersion,
      BuildInfoKey.action("lastCommitSha")(GitPlugin.gitCommitHash)
    ),
    buildInfoPackage :=
      s"${organization.value}.build",
    buildInfoOptions += BuildInfoOption.BuildTime,
    buildInfoOptions += BuildInfoOption.ToJson
  )

  private lazy val headerSettings: Seq[Def.Setting[_]] = Seq(
    startYear := Some(2018),
    licenses  := Nil,
    headerLicense := Some(
      HeaderLicense.Custom(
        "Copyright (c) 2018 ROUND INC.. All rights reserved."
      )
    ),
    headerMappings := headerMappings.value ++ Map(
      FileType("sbt")      -> HeaderCommentStyle.cppStyleLineComment,
      HeaderFileType.java  -> HeaderCommentStyle.cppStyleLineComment,
      HeaderFileType.scala -> HeaderCommentStyle.cppStyleLineComment
    )
  )

  // See https://docs.scala-lang.org/overviews/compiler-options/index.html
  private lazy val optOptions: Seq[String] =
    if (sys.env.contains("HELM_VERSION")) {
      Seq(
        "-opt:l:inline",
        "-opt-inline-from:**",
        "-opt:unreachable-code",
        "-opt:simplify-jumps",
        "-opt:compact-locals",
        "-opt:copy-propagation",
        "-opt:redundant-casts",
        "-opt:box-unbox",
        "-opt:nullness-tracking",
        "-opt:closure-invocations"
      )
    } else {
      Seq.empty
    }

  private lazy val scalacOptionsFor212: Seq[String] =
    Seq(
      "-Xlint:constant",
      "-Ywarn-extra-implicit",
      "-Ywarn-unused:implicits",
      "-Ywarn-unused:imports",
      "-Ywarn-unused:locals",
      "-Ywarn-unused:params",
      "-Ywarn-unused:patvars",
      "-Ywarn-unused:privates"
    ) ++ optOptions

  private lazy val commonScalacOptions: Seq[String] =
    Seq(
      "-deprecation",
      "-encoding",
      "utf-8",
      "-explaintypes",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-unchecked",
      "-Xfuture",
      "-Xlint:adapted-args",
      "-Xlint:by-name-right-associative",
      "-Xlint:delayedinit-select",
      "-Xlint:doc-detached",
      "-Xlint:inaccessible",
      "-Xlint:infer-any",
      "-Xlint:missing-interpolator",
      "-Xlint:nullary-override",
      "-Xlint:nullary-unit",
      "-Xlint:option-implicit",
      "-Xlint:package-object-classes",
      "-Xlint:poly-implicit-overload",
      "-Xlint:private-shadow",
      "-Xlint:stars-align",
      "-Xlint:type-parameter-shadow",
      "-Xlint:unsound-match",
      "-Yno-adapted-args",
      "-Ypartial-unification",
      "-Yrangepos",
      "-Ywarn-dead-code",
      "-Ywarn-inaccessible",
      "-Ywarn-infer-any",
      "-Ywarn-nullary-override",
      "-Ywarn-nullary-unit",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard"
    )

  implicit final class IntegrationTestOps(private val p: Project) extends AnyVal {
    import Dependencies.Testing
    import org.scalafmt.sbt.ScalafmtPlugin

    /**
      * Enable integration test configuration and a default set of settings.
      */
    def enableIntegrationTests: Project =
      p.configs(IntegrationTest)
        .settings(
          inConfig(IntegrationTest)(
            Defaults.testSettings ++
            headerSettings ++
            ScalafmtPlugin.scalafmtConfigSettings
          ),
          Defaults.itSettings,
          IntegrationTest / testOptions := (Test / testOptions).value,
          libraryDependencies ++= Seq(
            Testing.scalaCheck % "it,test",
            Testing.scalatest  % "it,test"
          )
        )
  }
}
