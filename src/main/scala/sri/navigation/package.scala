package sri

import sri.core.{ComponentConstructor, ReactElement}
import sri.macros.{OptDefault, OptionalParam}
import sri.navigation.navigators.NavigationNavigatorConstructor
import sri.universal.PropTypes
import sri.universal.apis.AnimatedValue

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.{ConstructorTag, |}

package object navigation {

  type NavigationAnimatedValue = AnimatedValue
  type NavigationSceneRendererProps = NavigationTransitionProps
  type NavigationAnimationSetter =
    js.Function3[NavigationAnimatedValue /*position*/,
                 NavigationState /*newState*/,
                 NavigationState /*lastState*/,
                 Unit]
  type NavigationSceneRenderer =
    js.Function1[NavigationSceneRendererProps /*props*/,
                 js.UndefOr[ReactElement]]
  type NavigationStyleInterpolator =
    js.Function1[NavigationSceneRendererProps /*props*/, js.Object]

  type NavigationDispatch[A] = js.Function1[A /*action*/, Boolean]

  type NavigationPathsConfig = js.Dictionary[String]

  type NavigationRouteConfigMap = js.Dictionary[NavigationScreenRouteConfig]

  type NavigationComponent =
    NavigationScreenComponentConstructor | NavigationNavigatorConstructor

  type NavigationScreenOption[T, P <: js.Object] =
    js.Function1[NavigationScreenProp[P], T] |
      js.Function2[NavigationScreenProp[P], js.Object, T] |
      js.Function3[NavigationScreenProp[P],
                   js.Object,
                   js.UndefOr[NavigationRouter],
                   T] | T

  type NavigationStackAction =
    NavigationInitAction | NavigationNavigateAction | NavigationBackAction | NavigationSetParamsAction | NavigationResetAction

  type NavigationTabAction =
    NavigationInitAction | NavigationNavigateAction | NavigationBackAction

  type NavigationAction =
    NavigationInitAction | NavigationStackAction | NavigationTabAction

  type NavigationRouteConfig =
    NavigationScreenRouteConfig

  type Navigation[P <: js.Object] = NavigationScreenProp[P]

  @inline
  def registerStackScreen[
      C <: NavigationScreenComponent[_ <: js.Object, _]: ConstructorTag](
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationStackScreenOptions] =
        OptDefault,
      navigationOptionsDynamic: OptionalParam[
        NavigationScreenConfigProps[C] => NavigationStackScreenOptions] =
        OptDefault)(implicit ctag: ClassTag[C])
    : (String, NavigationStackScreenRouteConfig) = {
    registerRoute(ctag.runtimeClass.getName,
                  js.constructorTag[C].constructor,
                  path,
                  navigationOptions,
                  navigationOptionsDynamic)
      .asInstanceOf[(String, NavigationStackScreenRouteConfig)]

  }

  @inline
  def registerTabScreen[C <: NavigationScreenComponent[_, _]: ConstructorTag](
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationTabScreenOptions] = OptDefault,
      navigationOptionsDynamic: OptionalParam[
        NavigationScreenConfigProps[C] => NavigationTabScreenOptions] =
        OptDefault)(
      implicit ctag: ClassTag[C]): (String, NavigationTabScreenRouteConfig) = {
    registerRoute(ctag.runtimeClass.getName,
                  js.constructorTag[C].constructor,
                  path,
                  navigationOptions,
                  navigationOptionsDynamic)
      .asInstanceOf[(String, NavigationTabScreenRouteConfig)]

  }

  @inline
  def registerDrawerScreen[
      C <: NavigationScreenComponent[_, _]: ConstructorTag](
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationDrawerScreenOptions] =
        OptDefault,
      navigationOptionsDynamic: OptionalParam[
        NavigationScreenConfigProps[C] => NavigationTabScreenOptions] =
        OptDefault)(implicit ctag: ClassTag[C])
    : (String, NavigationDrawerScreenRouteConfig) = {
    registerRoute(ctag.runtimeClass.getName,
                  js.constructorTag[C].constructor,
                  path,
                  navigationOptions,
                  navigationOptionsDynamic)
      .asInstanceOf[(String, NavigationDrawerScreenRouteConfig)]

  }

  @inline
  def registerNavigatorAsDrawerScreen(
      name: String,
      navigator: NavigationNavigatorConstructor,
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationDrawerScreenOptions] =
        OptDefault): (String, NavigationDrawerScreenRouteConfig) =
    registerRoute(name,
                  comp = navigator,
                  path = path,
                  navigationOptions = navigationOptions)
      .asInstanceOf[(String, NavigationDrawerScreenRouteConfig)]

  @inline
  def registerNavigatorAsTabScreen(
      name: String,
      navigator: NavigationNavigatorConstructor,
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationTabScreenOptions] = OptDefault)
    : (String, NavigationTabScreenRouteConfig) =
    registerRoute(name,
                  comp = navigator,
                  path = path,
                  navigationOptions = navigationOptions)
      .asInstanceOf[(String, NavigationTabScreenRouteConfig)]

  @inline
  def registerNavigatorAsStackScreen(
      name: String,
      navigator: NavigationNavigatorConstructor,
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationDrawerScreenOptions] =
        OptDefault): (String, NavigationStackScreenRouteConfig) =
    registerRoute(name,
                  comp = navigator,
                  path = path,
                  navigationOptions = navigationOptions)
      .asInstanceOf[(String, NavigationStackScreenRouteConfig)]

  @inline
  private def registerRoute[T <: NavigationScreenOptions, C <: ScreenClass](
      name: String,
      comp: js.Any,
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[T] = OptDefault,
      navigationOptionsDynamic: OptionalParam[
        NavigationScreenConfigProps[C] => T] = OptDefault)
    : (String, NavigationRouteConfig) =
    name -> NavigationScreenRouteConfig(screen = comp,
                                        path = path,
                                        navigationOptions = navigationOptions,
                                        navigationOptionsDynamic =
                                          navigationOptionsDynamic)

  @inline
  def getScreenName[C <: NavigationScreenComponent[_, _]: ConstructorTag](
      implicit ctag: ClassTag[C]) = ctag.runtimeClass.getName

  @js.native
  @JSImport("react-navigation", "NavigationActions")
  object NavigationActions extends js.Object {
    def reset(payload: js.Object): NavigationResetAction = js.native
    def navigate(payload: js.Object): NavigationAction = js.native
  }

  @inline def DRAWER_OPEN = "DrawerOpen"
  @inline def DRAWER_CLOSE = "DrawerClose"

  sealed trait NavigationScreenComponentConstructor
      extends ComponentConstructor

  def screenConstructor[C <: ScreenClass: js.ConstructorTag] =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[NavigationScreenComponentConstructor]

  def navigationNavigatorComponentConstructor[
      C <: NavigatorViewComponentClass: js.ConstructorTag] =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[NavigationNavigatorComponentConstructor]

  val navigationContextType =
    js.Dictionary("navigation" -> PropTypes.`object`.isRequired)

}
