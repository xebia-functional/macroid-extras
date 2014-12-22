package com.fortysevendeg.macroid.extras

import macroid.AppContext

trait AppContextProvider {

  implicit val appContextProvider : AppContext

}
