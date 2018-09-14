import com.round.Dependencies.SbtPlugin._

dependencyOverrides := com.round.DependencyOverrides.settings

addSbtPlugin(buildInfo)
addSbtPlugin(coursier)
addSbtPlugin(dynver)
addSbtPlugin(fs2gRPC)
addSbtPlugin(packager)
addSbtPlugin(partialUnification)
addSbtPlugin(sbtHeader)
addSbtPlugin(sbtRevolver)
addSbtPlugin(scalafix)
addSbtPlugin(scalafmt)
addSbtPlugin(scalastyle)
