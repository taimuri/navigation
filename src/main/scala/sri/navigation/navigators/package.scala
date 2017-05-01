package sri.navigation

import sri.core.{ComponentConstructor, ReactClass, ReactElement}
import sri.macros.{FunctionObjectMacro, OptDefault, OptionalParam}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

package object navigators {

  @ScalaJSDefined
  trait NavigationNavigatorConstructor extends ComponentConstructor {
    override type PropsType = NavigationNavigatorProps
  }

  @ScalaJSDefined
  trait StackNavigatorConfig
      extends NavigationContainerConfig
      with NavigationStackViewConfig
      with NavigationStackRouterConfig

  object StackNavigatorConfig {
    @inline
    def apply(mode: OptionalParam[NavigationStackViewConfigMode] = OptDefault,
              headerMode: OptionalParam[HeaderMode] = OptDefault,
              cardStyle: OptionalParam[js.Any] = OptDefault,
              onTransitionStart: OptionalParam[() => _] = OptDefault,
              onTransitionEnd: OptionalParam[() => _] = OptDefault,
              headerComponent: OptionalParam[js.Object] = OptDefault,
              initialRouteName: OptionalParam[String] = OptDefault,
              initialRouteParams: OptionalParam[js.Object] = OptDefault,
              paths: OptionalParam[NavigationPathsConfig] = OptDefault,
              navigationOptions: OptionalParam[NavigationStackScreenOptions] =
                OptDefault): StackNavigatorConfig = {
      val p = FunctionObjectMacro()
      p.asInstanceOf[StackNavigatorConfig]
    }
  }

  @JSImport("react-navigation", "StackNavigator")
  @js.native
  object StackNavigatorJS extends js.Object {

    def apply(routeConfigMap: NavigationRouteConfigMap,
              stackConfig: StackNavigatorConfig = ???)
      : NavigationNavigatorConstructor =
      js.native
  }

  @inline
  def StackNavigator(routes: (String, NavigationStackScreenRouteConfig)*) =
    StackNavigatorJS(js.Dictionary(routes: _*))

  @inline
  def StackNavigator(stackConfig: StackNavigatorConfig,
                     routes: (String, NavigationStackScreenRouteConfig)*) =
    StackNavigatorJS(js.Dictionary(routes: _*), stackConfig)

  @ScalaJSDefined
  trait TabNavigatorConfig
      extends NavigationTabRouterConfig
      with TabViewConfig
      with NavigationContainerConfig

  object TabNavigatorConfig {
    @inline
    def apply(
        initialRouteName: OptionalParam[String] = OptDefault,
        iconStyle: OptionalParam[js.Any] = OptDefault,
        paths: OptionalParam[NavigationPathsConfig] = OptDefault,
        navigationOptions: OptionalParam[NavigationTabScreenOptions] =
          OptDefault,
        order: OptionalParam[js.Array[String]] = OptDefault,
        tabBarComponent: OptionalParam[ReactClass] = OptDefault,
        tabBarPosition: OptionalParam[TabBarPosition] = OptDefault,
        tabBarOptions: OptionalParam[js.Object] = OptDefault,
        swipeEnabled: OptionalParam[Boolean] = OptDefault,
        animationEnabled: OptionalParam[Boolean] = OptDefault,
        `lazy`: OptionalParam[Boolean] = OptDefault): TabNavigatorConfig = {
      val p = FunctionObjectMacro()
      p.asInstanceOf[TabNavigatorConfig]
    }
  }

  @JSImport("react-navigation", "TabNavigator")
  @js.native
  object TabNavigatorJS extends js.Object {

    def apply(
        routeConfigMap: NavigationRouteConfigMap,
        tabConfig: TabNavigatorConfig = ???): NavigationNavigatorConstructor =
      js.native
  }

  @inline
  def TabNavigator(routes: (String, NavigationTabScreenRouteConfig)*) =
    TabNavigatorJS(js.Dictionary(routes: _*))

  @inline
  def TabNavigator(tabConfig: TabNavigatorConfig,
                   routes: (String, NavigationTabScreenRouteConfig)*) =
    TabNavigatorJS(js.Dictionary(routes: _*), tabConfig)

  @ScalaJSDefined
  trait DrawerNavigatorConfig
      extends NavigationTabRouterConfig
      with DrawerViewConfig
      with NavigationContainerConfig

  object DrawerNavigatorConfig {
    @inline
    def apply(
        initialRouteName: OptionalParam[String] = OptDefault,
        labelStyle: OptionalParam[js.Any] = OptDefault,
        paths: OptionalParam[NavigationPathsConfig] = OptDefault,
        navigationOptions: OptionalParam[NavigationDrawerScreenOptions] =
          OptDefault,
        order: OptionalParam[js.Array[String]] = OptDefault,
        drawerWidth: OptionalParam[Double] = OptDefault,
        drawerPosition: OptionalParam[DrawerPosition] = OptDefault,
        contentComponent: OptionalParam[Navigation[_] => ReactElement] =
          OptDefault,
        contentOptions: OptionalParam[js.Object] = OptDefault,
        style: OptionalParam[js.Any] = OptDefault): DrawerNavigatorConfig = {
      val p = FunctionObjectMacro()
      p.asInstanceOf[DrawerNavigatorConfig]
    }
  }

  @JSImport("react-navigation", "DrawerNavigator")
  @js.native
  object DrawerNavigatorJS extends js.Object {

    def apply(routeConfigMap: NavigationRouteConfigMap,
              drawerConfig: DrawerNavigatorConfig = ???)
      : NavigationNavigatorConstructor =
      js.native
  }

  @inline
  def DrawerNavigator(routes: (String, NavigationDrawerScreenRouteConfig)*) =
    DrawerNavigatorJS(js.Dictionary(routes: _*))

  @inline
  def DrawerNavigator(tabConfig: DrawerNavigatorConfig,
                      routes: (String, NavigationDrawerScreenRouteConfig)*) =
    DrawerNavigatorJS(js.Dictionary(routes: _*), tabConfig)
}
