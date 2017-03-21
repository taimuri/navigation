package sri

import sri.core.{ComponentConstructor, ReactElement}
import sri.macros.{OptDefault, OptionalParam}
import sri.navigation.navigators.NavigationNavigatorClass
import sri.universal.apis.AnimatedValue

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}
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

  type NavigationRouteConfigMap = js.Dictionary[NavigationRouteConfig]

  type NavigationComponent =
    NavigationScreenComponentConstructor | NavigationNavigatorClass

  type NavigationScreenOption[T, P <: js.Object] =
    js.Function1[NavigationScreenProp[NavigationRoute[P]], T] |
      js.Function2[NavigationScreenProp[NavigationRoute[P]], js.Object, T] |
      js.Function3[NavigationScreenProp[NavigationRoute[P]],
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
    NavigationScreenRouteConfig | NavigationLazyScreenRouteConfig

  type Navigation[P <: js.Object] = NavigationScreenProp[NavigationRoute[P]]

  @inline
  def registerScreen[C <: NavigationScreenComponent[_, _]: ConstructorTag](
      implicit ctag: ClassTag[C]): (String, NavigationRouteConfig) = {
    registerRoute(ctag.runtimeClass.getName, js.constructorTag[C].constructor)
  }

  @inline
  def registerScreen[C <: NavigationScreenComponent[_, _]: ConstructorTag](
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationScreenOptions] = OptDefault)(
      implicit ctag: ClassTag[C]): (String, NavigationRouteConfig) = {
    registerRoute(ctag.runtimeClass.getName,
                  js.constructorTag[C].constructor,
                  path,
                  navigationOptions)

  }

  @inline
  def registerNavigator(
      name: String,
      navigator: NavigationNavigatorClass,
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationScreenOptions] = OptDefault)
    : (String, NavigationRouteConfig) =
    registerRoute(name,
                  comp = navigator,
                  path = path,
                  navigationOptions = navigationOptions)

  @inline
  private def registerRoute(
      name: String,
      comp: js.Any,
      path: OptionalParam[String] = OptDefault,
      navigationOptions: OptionalParam[NavigationScreenOptions] = OptDefault)
    : (String, NavigationRouteConfig) =
    name -> NavigationScreenRouteConfig(screen = comp,
                                        path = path,
                                        navigationOptions = navigationOptions)

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

  @ScalaJSDefined
  sealed trait NavigationScreenComponentConstructor
      extends ComponentConstructor

  def screenConstructor[C <: ScreenClass: js.ConstructorTag] =
    js.constructorTag[C]
      .constructor
      .asInstanceOf[NavigationScreenComponentConstructor]

}