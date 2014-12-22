package com.fortysevendeg.macroid.extras

import macroid.LogTag

trait Tag {
  implicit val logTag = LogTag("ApiDemos")
}

object Tag extends Tag
