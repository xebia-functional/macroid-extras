package com.fortysevendeg.macroid.extras

import android.os.Bundle
import android.support.v4.app.{Fragment, FragmentManager}
import macroid.{AppContext, ActivityContext, FragmentBuilder, FragmentManagerContext}

object ExtraFragment {

  sealed trait FindBy

  case object FIND_BY_ID extends FindBy

  case object FIND_BY_TAG extends FindBy

  def addFragment[F <: Fragment](
      builder: FragmentBuilder[F],
      args: Option[Bundle] = None,
      id: Option[Int] = None,
      tag: Option[String] = None)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]) = {
    builder.pass(args.getOrElse(new Bundle())).factory map {
      managerContext.manager.beginTransaction().add(id.getOrElse(0), _, tag.getOrElse("")).commit()
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
    builder.pass(args).factory.map {
      fragment =>
        managerContext.manager.beginTransaction().replace(id, fragment, tag.orNull).commit()
    }
  }

  @deprecated("You should instead use `findTypedFragmentByTag`")
  def findFragmentByTag(tag: String)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]): Option[Fragment] = {
    Option(managerContext.manager.findFragmentByTag(tag))
  }

  @deprecated("You should instead use `findTypedFragmentById`")
  def findFragmentById(id: Int)
      (implicit context: ActivityContext, managerContext: FragmentManagerContext[Fragment, FragmentManager]): Option[Fragment] = {
    Option(managerContext.manager.findFragmentById(id))
  }

  def findTypedFragmentById[T <: Fragment](findParam: Int)
      (implicit appContext: AppContext, context: ActivityContext, fragmentManager: FragmentManagerContext[Fragment, FragmentManager]): Option[T] =
    findFragmentById(findParam) map (_.asInstanceOf[T])

  def findTypedFragmentByTag[T <: Fragment](findParam: String)
      (implicit appContext: AppContext, context: ActivityContext, fragmentManager: FragmentManagerContext[Fragment, FragmentManager]): Option[T] =
    findFragmentByTag(findParam) map (_.asInstanceOf[T])
}
