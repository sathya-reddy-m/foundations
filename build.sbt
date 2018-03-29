import Dependencies._

lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion       := "2.12.4",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions", "-language:existentials",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Yno-adapted-args",
    //    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture"
  ),
  addCompilerPlugin(kindProjector),
  libraryDependencies += scalaTest % Test
)

lazy val `fp-basis` = project.in(file("."))
  .settings(moduleName := "fp-basis")
  .settings(baseSettings: _*)
  .aggregate(exercises, slides)
  .dependsOn(exercises, slides)

lazy val exercises = project
  .settings(moduleName := "fp-basis-exercises")
  .settings(baseSettings: _*)
  .settings(libraryDependencies ++= Seq(
    "org.typelevel"  %% "cats"       % "0.9.0",
    "org.apache.poi"  % "poi-ooxml"  % "3.15",
    "org.scalatest"  %% "scalatest"  % "3.0.1"  % "test"
  ))


lazy val slides = project
  .settings(moduleName := "fp-basis-slides")
  .settings(baseSettings: _*)
  .settings(
    tutSourceDirectory := baseDirectory.value / "tut",
    tutTargetDirectory := baseDirectory.value / "../docs"
  )
  .enablePlugins(TutPlugin)