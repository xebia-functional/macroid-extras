import Libraries.android._
import Libraries.macroid._

android.Plugin.androidBuildAar

platformTarget in Android := Versions.androidPlatformV

name := "macroid-extras"

organization := "com.fortysevendeg"

organizationName := "47 Degrees"

organizationHomepage := Some(new URL("http://47deg.com"))

version := Versions.appV

scalaVersion := Versions.scalaV

scalacOptions ++= Seq("-feature", "-deprecation")

credentials += Credentials(new File(Path.userHome.absolutePath + "/.ivy2/.credentials"))

resolvers ++= Seq(
  Resolver.mavenLocal,
  DefaultMavenRepository,
  Resolver.typesafeRepo("releases"),
  Resolver.typesafeRepo("snapshots"),
  Resolver.typesafeIvyRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.defaultLocal,
  "jcenter" at "http://jcenter.bintray.com",
  "47deg Public" at "http://clinker.47deg.com/nexus/content/groups/public"
)

libraryDependencies ++= Seq(
  aar(macroidRoot),
  aar(androidAppCompat),
  aar(androidCardView),
  aar(androidRecyclerview))

publishMavenStyle := true

publishTo <<= version {
  v: String =>
    if (v.trim.endsWith("SNAPSHOT"))
      Some("47deg Public Snapshot Repository" at "http://clinker.47deg.com/nexus/content/repositories/snapshots")
    else
      Some("47deg Public Release Repository" at "http://clinker.47deg.com/nexus/content/repositories/releases")
}

startYear := Some(2015)

description := "47 Degrees Macroid Extras"

homepage := Some(url("http://47deg.com"))

scmInfo := Some(ScmInfo(url("https://github.com/47deg/macroid-extras"), "https://github.com/47deg/macroid-extras.git"))

pomExtra :=
    <developers>
      <developer>
        <name>47 Degrees (twitter: @47deg)</name>
        <email>hello@47deg.com</email>
      </developer>
      <developer>
        <name>47 Degrees</name>
      </developer>
    </developers>