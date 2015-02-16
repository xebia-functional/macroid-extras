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

import android.app.Activity
import android.content.{ComponentName, Intent}
import android.graphics.drawable.Drawable
import android.widget.Toast
import macroid.{AppContext, ActivityContext, Ui}

object UIActionsExtras {

  def uiStartActivity[T <: Activity]()(implicit c: ActivityContext, m: Manifest[T]): Ui[Unit] =
    Ui(c.get.startActivity(new Intent(c.get, m.runtimeClass)))

  def uiStartActivityForResult[T <: Activity](result: Int)(implicit c: ActivityContext, m: Manifest[T]): Ui[Unit] =
    Ui(c.get.startActivityForResult(new Intent(c.get, m.runtimeClass), result))

  def uiShortToast(msg: Int)(implicit c: AppContext): Ui[Unit] =
    Ui(Toast.makeText(c.get, msg, Toast.LENGTH_SHORT).show())

  def uiLongToast(msg: Int)(implicit c: AppContext): Ui[Unit] =
    Ui(Toast.makeText(c.get, msg, Toast.LENGTH_LONG).show())

  def uiShortToast(msg: String)(implicit c: AppContext): Ui[Unit] =
    Ui(Toast.makeText(c.get, msg, Toast.LENGTH_SHORT).show())

  def uiLongToast(msg: String)(implicit c: AppContext): Ui[Unit] =
    Ui(Toast.makeText(c.get, msg, Toast.LENGTH_LONG).show())

}

object ActionsExtras {

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

  def aShortToast(msg: Int)(implicit c: AppContext): Unit =
    Toast.makeText(c.get, msg, Toast.LENGTH_SHORT).show()

  def aLongToast(msg: Int)(implicit c: AppContext): Unit =
    Toast.makeText(c.get, msg, Toast.LENGTH_LONG).show()

  def aShortToast(msg: String)(implicit c: AppContext): Unit =
    Toast.makeText(c.get, msg, Toast.LENGTH_SHORT).show()

  def aLongToast(msg: String)(implicit c: AppContext): Unit =
    Toast.makeText(c.get, msg, Toast.LENGTH_LONG).show()

}

object ResourcesExtras {

  private def resGetResource[A, B](resource: String, resourceType: String)(f: (AppContext, Int) ⇒ B)
      (implicit c: AppContext): Option[B] = {
    val resourceId = c.get.getResources.getIdentifier(resource, resourceType, c.get.getPackageName)

    resourceId match {
      case 0 ⇒ None
      case _ ⇒ Some(f(c, resourceId))
    }
  }

  def resGetBoolean(resourceId: Int)(implicit c: AppContext): Boolean = c.get.getResources.getBoolean(resourceId)

  def resGetBoolean(resource: String)(implicit c: AppContext): Option[Boolean] =
    resGetResource(resource, "boolean")((c, resourceId) ⇒ c.get.getResources.getBoolean(resourceId))

  def resGetColor(resourceId: Int)(implicit c: AppContext): Int = c.get.getResources.getColor(resourceId)

  def resGetColor(resource: String)(implicit c: AppContext): Option[Int] =
    resGetResource(resource, "color")((c, resourceId) ⇒ c.get.getResources.getColor(resourceId))

  def resGetDimension(resourceId: Int)(implicit c: AppContext): Float = c.get.getResources.getDimension(resourceId)

  def resGetDimension(resource: String)(implicit c: AppContext): Option[Float] =
    resGetResource(resource, "dimen")((c, resourceId) ⇒ c.get.getResources.getDimension(resourceId))

  def resGetDimensionPixelSize(resourceId: Int)(implicit c: AppContext): Int = c.get.getResources.getDimensionPixelSize(resourceId)

  def resGetDimensionPixelSize(resource: String)(implicit c: AppContext): Option[Int] =
    resGetResource(resource, "dimen")((c, resourceId) ⇒ c.get.getResources.getDimensionPixelSize(resourceId))

  def resGetDrawable(resourceId: Int)(implicit c: AppContext): Drawable = c.get.getResources.getDrawable(resourceId)

  def resGetDrawable(resource: String)(implicit c: AppContext): Option[Drawable] =
    resGetResource(resource, "drawable")((c, resourceId) ⇒ c.get.getResources.getDrawable(resourceId))

  def resGetIdentifier(resource: String, resourceType: String)(implicit c: AppContext): Option[Int] = {
    resGetResource(resource, resourceType)((_, resourceId) ⇒ resourceId)
  }

  def resGetInteger(resourceId: Int)(implicit c: AppContext): Int = c.get.getResources.getInteger(resourceId)

  def resGetInteger(resource: String)(implicit c: AppContext): Option[Int] =
    resGetResource(resource, "integer")((c, resourceId) ⇒ c.get.getResources.getInteger(resourceId))

  def resGetResourcePackageName(resourceId: Int)(implicit c: AppContext): String =
    c.get.getResources.getResourcePackageName(resourceId)

  def resGetString(resourceId: Int)(implicit c: AppContext): String = c.get.getResources.getString(resourceId)

  def resGetString(resource: String)(implicit c: AppContext): Option[String] =
    resGetResource(resource, "string")((c, resourceId) ⇒ c.get.getResources.getString(resourceId))

  def resGetString(resourceId: Int, formatArgs: AnyRef*)(implicit c: AppContext): String =
    c.get.getResources.getString(resourceId, formatArgs:_*)

  def resGetString(resource: String, formatArgs: AnyRef*)(implicit c: AppContext): Option[String] = {
    resGetResource(resource, "string")((c, resourceId) ⇒ c.get.getResources.getString(resourceId, formatArgs:_*))
  }
}