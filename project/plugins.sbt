import com.round.Dependencies.Fs2gRPC
import com.round.Dependencies.SbtPlugin._

dependencyOverrides := com.round.DependencyOverrides.settings

addSbtPlugin(buildInfo)
addSbtPlugin(coursier)
addSbtPlugin(dynver)
addSbtPlugin(explicitDeps)
addSbtPlugin(Fs2gRPC.plugin)
addSbtPlugin(packager)
addSbtPlugin(partialUnification)
addSbtPlugin(sbtHeader)
addSbtPlugin(scalafix)
addSbtPlugin(scalafmt)
addSbtPlugin(scalastyle)
