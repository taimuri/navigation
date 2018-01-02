name := "navigation"

//version := "2017.7.0-SNAPSHOT"

enablePlugins(ScalaJSPlugin)

val scala211 = "2.11.11"

val scala212 = "2.12.2"

scalaVersion := scala211

crossScalaVersions := Seq(scala211, scala212)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions"
)

//Dependencies

libraryDependencies ++= Seq(
  "scalajs-react-interface" %%% "universal" % "2017.7.9-RC" % Provided,
  "scalajs-react-interface" %%% "core" % "2017.7.9-RC" % Provided,
  "scalajs-react-interface" %%% "relay" % "2017.7.9-RC" % Provided)

//bintray
resolvers += Resolver.jcenterRepo

organization := "scalajs-react-interface"

licenses += ("Apache-2.0", url(
  "https://www.apache.org/licenses/LICENSE-2.0.html"))

bintrayOrganization := Some("scalajs-react-interface")

bintrayRepository := "maven"

publishArtifact in Test := false

//Test
resolvers += Resolver.bintrayRepo("scalajs-react-interface", "maven")
scalaJSUseMainModuleInitializer in Test := true

scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule))

val TEST_FILE = s"./sjs.test.js"

artifactPath in Test in fastOptJS := new File(TEST_FILE)
artifactPath in Test in fullOptJS := new File(TEST_FILE)

val testDev = Def.taskKey[Unit]("test in dev mode")
val testProd = Def.taskKey[Unit]("test in prod mode")

testDev := {
  (fastOptJS in Test).value
  runJest()
}

testProd := {
  (fullOptJS in Test).value
  runJest()
}

def runJest() = {
  import sys.process._
  val jestResult = "npm test".!
  if (jestResult != 0) throw new IllegalStateException("Jest Suite failed")
}

resolvers += Resolver.bintrayRepo("scalajs-react-interface", "maven")
resolvers += Resolver.bintrayRepo("scalajs-jest", "maven")

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.3" % Test,
  "scalajs-jest" %%% "core" % "2017.7.9-beta" % Test
)
//scalaJSStage in Global := FastOptStage
scalaJSStage in Global := FullOptStage
