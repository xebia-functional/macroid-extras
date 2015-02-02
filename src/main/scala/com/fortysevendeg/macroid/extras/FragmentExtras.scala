/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortysevendeg.macroid.extras

import android.os.Bundle
import android.support.v4.app.{Fragment, FragmentManager}
import macroid.{ActivityContext, FragmentBuilder, FragmentManagerContext}

object FragmentExtras {

  def addFragment[F <: Fragment](
      builder: FragmentBuilder[F],
      args: Option[Bundle] = None,
      id: Option[Int] = None,
      tag: Option[String] = None)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    builder.pass(args getOrElse new Bundle()).factory map {
      managerContext.manager.beginTransaction().add(id.getOrElse(0), _, tag getOrElse "").commit()
    }
  }

  def removeFragment(fragment: Fragment)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    managerContext.manager.beginTransaction().remove(fragment).commit()
  }

  def replaceFragment[F <: Fragment](
      builder: FragmentBuilder[F],
      args: Bundle,
      id: Int,
      tag: Option[String] = None)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    builder.pass(args).factory map {
      fragment ⇒
        managerContext.manager.beginTransaction().replace(id, fragment, tag.orNull).commit()
    }
  }

  def findFragmentByTag[T <: Fragment](tag: String)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]): Option[T] =
    Option(managerContext.manager.findFragmentByTag(tag)) map (_.asInstanceOf[T])

  def findFragmentById[T <: Fragment](id: Int)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]): Option[T] =
    Option(managerContext.manager.findFragmentById(id)) map (_.asInstanceOf[T])
}