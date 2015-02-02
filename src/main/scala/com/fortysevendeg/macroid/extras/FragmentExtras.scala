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