**PROJECT DISCONTINUED** : Now, this module is included in Macroid[https://github.com/47deg/macroid], you can use it  including it in your project 

[![Macroid-Extras Status](https://circleci.com/gh/47deg/macroid-extras.svg?&style=shield&circle-token=64357a7024f51a97548b75794ef323b52295a23b "Macroid Extras Status")](https://circleci.com/gh/47deg/macroid-extras)

Macroid Extras Lib
---

An heterogenous collection of traits and objects extending the fantastic Scala on Android Lib [Macroid](https://github.com/macroid/macroid).
Macroid-Extras implements some common Tweaks and Snails commonly used in projects at [47 Degrees](http://47deg.com)

Used in:

- [Translate Bubble](https://github.com/47deg/translate-bubble-android)

Usage
======

Version 0.3 is using Macroid version `2.0.0-M5`

```scala

libraryDependencies ++= Seq(
  aar("com.fortysevendeg" %% "macroid-extras" % "0.3")

```

If you want to use the SNAPSHOT version you need to add the Sonatype SNAPSHOT repo

```scala

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  aar("com.fortysevendeg" %% "macroid-extras" % "0.4-SNAPSHOT")

```

License
======

Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
