package com.fortysevendeg.macroid.extras

import android.app.Activity
import android.content.{ComponentName, Intent}
import android.graphics.drawable.Drawable
import android.widget.Toast
import macroid.{AppContext, ActivityContext, Ui}

object ExtraUIActions {

  def uiStartActivity[T <: Activity]()(implicit c: ActivityContext, m: Manifest[T]): Ui[Unit] =
    Ui(c.get.startActivity(new Intent(c.get, m.runtimeClass)))

  def uiStartActivityForResult[T <: Activity](result: Int)(implicit c: ActivityContext, m: Manifest[T]): Ui[Unit] =
    Ui(c.get.startActivityForResult(new Intent(c.get, m.runtimeClass), result))

}

object ExtraActions {

  def aStartActivity[T <: Activity]()(implicit c: ActivityContext, m: Manifest[T]): Unit =
    c.get.startActivity(new Intent(c.get, m.runtimeClass))

  def aStartActivityForResult[T <: Activity](result: Int)(implicit c: ActivityContext, m: Manifest[T]): Unit =
    c.get.startActivityForResult(new Intent(c.get, m.runtimeClass), result)

  def aStartActivityFromComponentName[T <: Activity](componentName: ComponentName)
      (implicit c: ActivityContext): Unit = {
    val intent = new Intent()
    intent.setComponent(componentName)
    c.get.startActivity(intent)
  }

  def aStartActivityFromComponentNameForResult[T <: Activity](componentName: ComponentName, result: Int)
      (implicit c: ActivityContext): Unit = {
    val intent = new Intent()
    intent.setComponent(componentName)
    c.get.startActivityForResult(intent, result)
  }

  def aShortToast(msg: String)(implicit c: AppContext) =
    Toast.makeText(c.get, msg, Toast.LENGTH_SHORT).show()

  def aLongToast(msg: String)(implicit c: AppContext) =
    Toast.makeText(c.get, msg, Toast.LENGTH_LONG).show()

}

object ExtraResources {

  private def resGetResource[T, O](resource: String, resourceType: String)(f: (AppContext, Int) => O)
      (implicit c: AppContext): Option[O] = {
    val resourceId = c.get.getResources.getIdentifier(resource, resourceType, c.get.getPackageName)

    resourceId match {
      case 0 => None
      case _ => Some(f(c, resourceId))
    }
  }

  def resGetBoolean(resourceId: Int)(implicit c: AppContext): Boolean = c.get.getResources.getBoolean(resourceId)

  def resGetBoolean(resource: String)(implicit c: AppContext): Option[Boolean] =
    resGetResource(resource, "boolean")((c, resourceId) => c.get.getResources.getBoolean(resourceId))

  def resGetColor(resourceId: Int)(implicit c: AppContext): Int = c.get.getResources.getColor(resourceId)

  def resGetColor(resource: String)(implicit c: AppContext): Option[Int] =
    resGetResource(resource, "color")((c, resourceId) => c.get.getResources.getColor(resourceId))

  def resGetDimension(resourceId: Int)(implicit c: AppContext): Float = c.get.getResources.getDimension(resourceId)

  def resGetDimension(resource: String)(implicit c: AppContext): Option[Float] =
    resGetResource(resource, "dimen")((c, resourceId) => c.get.getResources.getDimension(resourceId))

  def resGetDrawable(resourceId: Int)(implicit c: AppContext): Drawable = c.get.getResources.getDrawable(resourceId)

  def resGetDrawable(resource: String)(implicit c: AppContext): Option[Drawable] =
    resGetResource(resource, "drawable")((c, resourceId) => c.get.getResources.getDrawable(resourceId))

  def resGetIdentifier(resource: String, resourceType: String)(implicit c: AppContext): Option[Int] = {
    val resourceId = c.get.getResources.getIdentifier(resource, resourceType, c.get.getPackageName)
    resourceId match {
      case 0 => None
      case _ => Some(resourceId)
    }
  }

  def resGetInteger(resourceId: Int)(implicit c: AppContext): Int = c.get.getResources.getInteger(resourceId)

  def resGetInteger(resource: String)(implicit c: AppContext): Option[Int] =
    resGetResource(resource, "integer")((c, resourceId) => c.get.getResources.getInteger(resourceId))

  def resGetResourcePackageName(resourceId: Int)(implicit c: AppContext): String =
    c.get.getResources.getResourcePackageName(resourceId)

  def resGetString(resourceId: Int)(implicit c: AppContext): String = c.get.getResources.getString(resourceId)

  def resGetString(resource: String)(implicit c: AppContext): Option[String] =
    resGetResource(resource, "string")((c, resourceId) => c.get.getResources.getString(resourceId))

  def resGetString(resourceId: Int, formatArgs: String*)(implicit c: AppContext): String =
    c.get.getResources.getString(resourceId, formatArgs)

  def resGetString(resource: String, formatArgs: String*)(implicit c: AppContext): Option[String] = {
    val resourceId = c.get.getResources.getIdentifier(resource, "string", c.get.getPackageName)
    resourceId match {
      case 0 => None
      case _ => Some(c.get.getResources.getString(resourceId, formatArgs))
    }
  }
}
