package sri.navigation

import sri.core.{CreateElementJS, JSComponent}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-navigation", "DrawerItems")
object DrawerItemsComponent extends JSComponent[js.Object]

object DrawerItems {
  @inline
  def apply(props: Navigation[_]) =
    CreateElementJS[DrawerItemsComponent.type](DrawerItemsComponent, props)
}
