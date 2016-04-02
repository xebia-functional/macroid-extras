import Libraries.android._
import Libraries.macroid._
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._

android.Plugin.androidBuildAar

platformTarget in Android := Versions.androidPlatformV

name := "macroid-extras"

version := Versions.appV

scalaVersion := Versions.scalaV

scalacOptions ++= Seq("-feature", "-deprecation")

resolvers ++= Seq(
  Resolver.mavenLocal,
  DefaultMavenRepository,
  Resolver.typesafeRepo("releases"),
  Resolver.typesafeRepo("snapshots"),
  Resolver.typesafeIvyRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.defaultLocal,
  "jcenter" at "http://jcenter.bintray.com"
)

libraryDependencies ++= Seq(
  aar(macroidRoot),
  aar(androidAppCompat),
  aar(androidCardView),
  aar(androidRecyclerview),
  aar(androidDesign))

licenses := Seq("Apache License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
  .setPreference(DoubleIndentClassDeclaration, false)
  .setPreference(MultilineScaladocCommentsStartOnFirstLine, true)
  .setPreference(RewriteArrowSymbols, true)