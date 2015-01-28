package com.fortysevendeg.macroid.extras

import android.support.v4.app.{Fragment, FragmentManager}
import macroid.{ActivityContext, FragmentBuilder, FragmentManagerContext}

object ExtraFragment {

  def addFragment[F <: Fragment](
      builder: FragmentBuilder[F],
      id: Option[Int] = None,
      tag: Option[String] = None)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    builder.factory map (managerContext.manager.beginTransaction().add(id.getOrElse(0), _, tag.getOrElse("")).commit())
  }

  def removeFragment(fragment: Fragment)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    managerContext.manager.beginTransaction().remove(fragment).commit()
  }

  def replaceFragment[F <: Fragment](
      builder: FragmentBuilder[F],
      id: Int,
      tag: Option[String] = None)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    builder.factory.map (managerContext.manager.beginTransaction().replace(id, _, tag.orNull).commit())
  }

  def findFragmentByTag[T <: Fragment](tag: String)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]): Option[T] =
    Option(managerContext.manager.findFragmentByTag(tag)) map (_.asInstanceOf[T])

  def findFragmentById[T <: Fragment](id: Int)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]): Option[T] =
    Option(managerContext.manager.findFragmentById(id)) map (_.asInstanceOf[T])
}