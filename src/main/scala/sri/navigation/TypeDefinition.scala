package sri.navigation

import sri.core.{ReactClass, ReactElement, ReactJSProps}
import sri.macros.{
  FunctionObjectMacro,
  rename,
  OptDefault => NoValue,
  OptionalParam => OP
}

import scala.scalajs.js
import scala.scalajs.js.Dynamic.literal
import scala.scalajs.js.{undefined, |, UndefOr => U}

trait NavigationState extends js.Object {
  val index: Double
  val routes: js.Array[NavigationRoute[js.Object]]
}

trait RouteBase extends js.Object {
  type ParamsType <: js.Object
}

trait NavigationRoute[Params <: js.Object] extends RouteBase {
  override type ParamsType = Params
  val key: String
  val routeName: String
  val path: U[String] = undefined
  val params: U[Params] = undefined
}

object NavigationRoute {
  @inline
  def apply[Params <: js.Object](key: String,
                                 routeName: String,
                                 path: OP[String] = NoValue,
                                 params: OP[Params] = NoValue,
                                 index: Double,
                                 routes: js.Array[NavigationRoute[js.Object]])
    : NavigationRoute[Params] = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationRoute[Params]]
  }
}

trait NavigationRouter extends js.Object {
  val getStateForAction: js.Function2[NavigationAction /*action*/,
                                      U[NavigationState] /*lastState*/,
                                      U[NavigationState]]
  val getActionForPathAndParams: js.Function2[String /*path*/,
                                              U[NavigationParams] /*params*/,
                                              U[NavigationAction]]
  val getPathAndParamsForState: js.Function1[NavigationState /*state*/,
                                             js.Object]
  val getComponentForRouteName: js.Function1[String /*routeName*/,
                                             NavigationComponent]
  val getComponentForState: js.Function1[NavigationState /*state*/,
                                         NavigationComponent]
  val getScreenConfig: js.Function2[
    NavigationScreenProp[NavigationRoute[js.Object]] /*navigation*/,
    String /*optionName*/,
    U[js.Any]]

}

trait IconOptions extends js.Object {

  val tintColor: String

  val focused: Boolean
}

trait NavigationScreenOptions extends js.Object

trait NavigationStackScreenOptions extends NavigationScreenOptions

object NavigationStackScreenOptions {
  @inline
  def apply(title: OP[String] = NoValue,
            header: OP[ReactElement] = NoValue,
            @rename("header") headerDyanmic: OP[HeaderProps => ReactElement] =
              NoValue,
            headerTitle: OP[String | ReactElement] = NoValue,
            headerTitleStyle: OP[js.Any] = NoValue,
            headerStyle: OP[js.Any] = NoValue,
            headerBackTitleStyle: OP[js.Any] = NoValue,
            headerTintColor: OP[String] = NoValue,
            headerVisible: OP[Boolean] = NoValue,
            gesturesEnabled: OP[Boolean] = NoValue,
            headerBackTitle: OP[String] = NoValue,
            headerPressColorAndroid: OP[String] = NoValue,
            headerLeft: OP[ReactElement] = NoValue,
            headerRight: OP[ReactElement] = NoValue)
    : NavigationStackScreenOptions = {
    import sri.universal.DangerousUnionToJSAnyImplicit._
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationStackScreenOptions]
  }
}

trait NavigationTabScreenOptions extends NavigationScreenOptions

object NavigationTabScreenOptions {
  @inline
  def apply(
      title: OP[String] = NoValue,
      tabBarIcon: OP[IconOptions => ReactElement] = NoValue,
      tabBarLabel: OP[String | ReactElement] = NoValue,
      tabBarVisible: OP[Boolean] = NoValue): NavigationTabScreenOptions = {
    import sri.universal.DangerousUnionToJSAnyImplicit._
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationTabScreenOptions]
  }
}

trait NavigationDrawerScreenOptions extends NavigationScreenOptions

object NavigationDrawerScreenOptions {
  @inline
  def apply(
      title: OP[String] = NoValue,
      drawerIcon: OP[IconOptions => ReactElement] = NoValue,
      drawerLabel: OP[
        String | ReactElement | js.Function1[IconOptions, ReactElement]] =
        NoValue): NavigationDrawerScreenOptions = {
    import sri.universal.DangerousUnionToJSAnyImplicit._
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationDrawerScreenOptions]
  }
}

trait GenericScreen extends ScreenClass {
  override type ParamsType = js.Object
}

//
//trait NavigationScreenComponent[T] extends js.Object {}

trait NavigationNavigator[T] extends js.Object {}

trait NavigationParams extends js.Object

trait NavigationNavigateAction extends js.Object {
  var `type`: String
  var routeName: String
  var params: U[NavigationParams] = undefined
  var action: U[NavigationNavigateAction] = undefined
}

object NavigationNavigateAction {

  def apply(routeName: String,
            params: U[NavigationParams] = undefined,
            action: U[NavigationNavigateAction] = undefined) =
    literal(`type` = "Navigate",
            routeName = routeName,
            params = params,
            action = action).asInstanceOf[NavigationNavigateAction]
}

trait NavigationBackAction extends js.Object {
  var `type`: String
  var key: U[String] = undefined
}

object NavigationBackAction {

  def apply(key: U[String] = undefined) =
    literal(`type` = "Back", key = key).asInstanceOf[NavigationBackAction]
}

trait NavigationSetParamsAction extends js.Object {
  var `type`: String
  var key: String
  var params: U[NavigationParams] = undefined
}

object NavigationSetParamsAction {
  def apply(key: String, params: U[NavigationParams] = undefined) =
    literal(`type` = "SetParams", key = key, params = params)
      .asInstanceOf[NavigationSetParamsAction]
}

trait NavigationInitAction extends js.Object {
  var `type`: String
}

object NavigationInitAction {
  def apply() = literal(`type` = "Init").asInstanceOf[NavigationInitAction]
}

trait NavigationResetAction extends js.Object {
  var `type`: String
  var index: Double
  var actions: js.Array[NavigationNavigateAction]
}

object NavigationResetAction {
  def apply(index: Double, actions: js.Array[NavigationNavigateAction]) =
    literal(`type` = "Reset", index = index, actions = actions)
      .asInstanceOf[NavigationResetAction]
}

trait NavigationContainerOptions extends js.Object {
  val URIPrefix: U[String] = undefined
}

object NavigationContainerOptions {
  @inline
  def apply(URIPrefix: OP[String] = NoValue): NavigationContainerOptions = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationContainerOptions]
  }
}

trait NavigationContainerConfig extends js.Object {
  val containerOptions: U[NavigationContainerOptions] = undefined
}

object NavigationContainerConfig {
  @inline
  def apply(containerOptions: OP[NavigationContainerOptions] = NoValue)
    : NavigationContainerConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationContainerConfig]
  }
}

trait NavigationStackViewConfigMode extends js.Object

object NavigationStackViewConfigMode {
  @inline def CARD = "card".asInstanceOf[NavigationStackViewConfigMode]
  @inline def MODAL = "modal".asInstanceOf[NavigationStackViewConfigMode]
}

trait HeaderMode extends js.Object

object HeaderMode {
  @inline def FLOAT = "float".asInstanceOf[HeaderMode]
  @inline def SCREEN = "screen".asInstanceOf[HeaderMode]
  @inline def NONE = "none".asInstanceOf[HeaderMode]
}

trait NavigationStackViewConfig extends js.Object {
  val mode: U[NavigationStackViewConfigMode] = undefined

  val headerMode: U[HeaderMode] = undefined
  val cardStyle: U[js.Any] = undefined
  val onTransitionStart: U[js.Function0[_]] = undefined
  val onTransitionEnd: U[js.Function0[_]] = undefined
  val headerComponent: U[js.Object] = undefined
}

object NavigationStackViewConfig {
  @inline
  def apply(
      mode: OP[NavigationStackViewConfigMode] = NoValue,
      headerMode: OP[HeaderMode] = NoValue,
      headerComponent: OP[js.Object] = NoValue): NavigationStackViewConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationStackViewConfig]
  }
}

trait NavigationStackRouterConfig extends js.Object {
  val initialRouteName: U[String] = undefined
  val initialRouteParams: U[NavigationParams] = undefined
  val paths: U[NavigationPathsConfig] = undefined
  val navigationOptions: U[NavigationStackScreenOptions] = undefined
}

object NavigationStackRouterConfig {
  @inline
  def apply(initialRouteName: OP[String] = NoValue,
            initialRouteParams: OP[NavigationParams] = NoValue,
            paths: OP[NavigationPathsConfig] = NoValue,
            navigationOptions: OP[NavigationStackScreenOptions] = NoValue)
    : NavigationStackRouterConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationStackRouterConfig]
  }
}

trait NavigationScreenRouteConfig extends js.Object {
  var screen: js.Any //TODO fix this
  var path: U[String] = undefined
  val navigationOptions: NavigationScreenOptions
}

trait NavigationStackScreenRouteConfig extends NavigationScreenRouteConfig

trait NavigationTabScreenRouteConfig extends NavigationScreenRouteConfig

trait NavigationDrawerScreenRouteConfig extends NavigationScreenRouteConfig

object NavigationScreenRouteConfig {
  @inline
  def apply[T <: NavigationScreenOptions, C <: ScreenClass](
      screen: js.Any,
      path: OP[String] = NoValue,
      navigationOptions: OP[T],
      @rename("navigationOptions") navigationOptionsDynamic: OP[
        NavigationScreenConfigProps[C] => T] = NoValue)
    : NavigationScreenRouteConfig = {
    import sri.universal.DangerousUnionToJSAnyImplicit._
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationScreenRouteConfig]
  }
}

trait NavigationLazyScreenRouteConfig extends js.Object {
  val getScreen: js.Function0[NavigationScreenComponent[_, _]] | NavigationNavigator[
    _]
  val navigationOptions: U[NavigationStackScreenOptions] = undefined
  val path: U[String] = undefined
}

object NavigationLazyScreenRouteConfig {
  import sri.universal.DangerousUnionToJSAnyImplicit._
  @inline
  def apply(
      getScreen: js.Function0[NavigationScreenComponent[_, _]] | NavigationNavigator[
        _],
      navigationOptions: OP[NavigationStackScreenOptions] = NoValue,
      path: OP[String] = NoValue): NavigationLazyScreenRouteConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationLazyScreenRouteConfig]
  }
}

trait NavigationTabRouterConfig extends js.Object {
  val initialRouteName: U[String] = undefined
  val paths: U[NavigationPathsConfig] = undefined
  val navigationOptions: U[NavigationTabScreenOptions] = undefined
  val order: U[js.Array[String]] = undefined
}

object NavigationTabRouterConfig {
  @inline
  def apply(
      initialRouteName: OP[String] = NoValue,
      paths: OP[NavigationPathsConfig] = NoValue,
      navigationOptions: OP[NavigationTabScreenOptions] = NoValue,
      order: OP[js.Array[String]] = NoValue): NavigationTabRouterConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationTabRouterConfig]
  }
}

trait NavigationProp[S] extends js.Object {
  val state: S
  val dispatch: NavigationDispatch[NavigationAction]
}

@js.native
trait NavigationScreenConfigProps[C <: ScreenClass] extends js.Object {
  val navigation: Navigation[C#ParamsType] = js.native
  val screenProps: js.Object = js.native
  val navigationOptions: NavigationScreenOptions = js.native
}

@js.native
trait NavigatorScreenProps[P <: js.Object] extends ReactJSProps {
  val navigation: NavigationScreenProp[P] = js.native
  val screenProps: js.Object = js.native
}

@js.native
trait NavigationScreenProp[P <: js.Object] extends js.Object {
  val state: NavigationRoute[P] = js.native
  val dispatch: NavigationDispatch[NavigationAction] = js.native
  def goBack(routeKey: String = ???): Boolean =
    js.native
  def navigate(routeName: String,
               params: Any = ???,
               action: NavigationAction = ???): Boolean = js.native
  def setParams(newParams: P): Boolean = js.native
}

trait NavigationNavigatorProps extends js.Object {
  val navigation: NavigationProp[NavigationState]
  val screenProps: js.Object
  val navigationOptions: js.Object
}

object NavigationNavigatorProps {
  @inline
  def apply(screenProps: OP[js.Object] = NoValue): NavigationNavigatorProps = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationNavigatorProps]
  }
}

trait NavigationLayout extends js.Object {
  val height: NavigationAnimatedValue
  val initHeight: Double
  val initWidth: Double
  val isMeasured: Boolean
  val width: NavigationAnimatedValue
}

trait NavigationScene extends js.Object {
  val index: Double
  val isActive: Boolean
  val isStale: Boolean
  val key: String
  val route: NavigationRoute[js.Object]
}

trait NavigationTransitionProps extends js.Object {
  val layout: NavigationLayout
  val navigationState: NavigationState
  val position: NavigationAnimatedValue
  val progress: NavigationAnimatedValue
  val scenes: js.Array[NavigationScene]
  val scene: NavigationScene
  val index: Double
  val gestureResponseDistance: U[Double] = undefined
}

trait NavigationPanHandlers extends js.Object {
  val onMoveShouldSetResponder: js.Function0[Unit]
  val onMoveShouldSetResponderCapture: js.Function0[Unit]
  val onResponderEnd: js.Function0[Unit]
  val onResponderGrant: js.Function0[Unit]
  val onResponderMove: js.Function0[Unit]
  val onResponderReject: js.Function0[Unit]
  val onResponderRelease: js.Function0[Unit]
  val onResponderStart: js.Function0[Unit]
  val onResponderTerminate: js.Function0[Unit]
  val onResponderTerminationRequest: js.Function0[Unit]
  val onStartShouldSetResponder: js.Function0[Unit]
  val onStartShouldSetResponderCapture: js.Function0[Unit]
}

trait NavigationTransitionSpec extends js.Object {
  val duration: U[Double] = undefined
  val easing: U[js.Function0[js.Any]] = undefined
  val timing: U[js.Function2[NavigationAnimatedValue /*value*/,
                             js.Any /*config*/,
                             js.Any]] = undefined
}

object NavigationTransitionSpec {
  @inline
  def apply(
      duration: OP[Double] = NoValue,
      easing: OP[js.Function0[js.Any]] = NoValue,
      timing: OP[
        (NavigationAnimatedValue /*value*/, js.Any /*config*/ ) => js.Any] =
        NoValue): NavigationTransitionSpec = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[NavigationTransitionSpec]
  }
}

trait NavigationGestureDirection extends js.Object

object NavigationGestureDirection {
  @inline def HORIZONTAL =
    "horizontal".asInstanceOf[NavigationGestureDirection]
  @inline def VERTICAL = "vertical".asInstanceOf[NavigationGestureDirection]

}

trait TabBarPosition extends js.Object

object TabBarPosition {
  @inline def TOP = "top".asInstanceOf[TabBarPosition]
  @inline def BOTTOM = "bottom".asInstanceOf[TabBarPosition]
}

trait TabBarOptions extends js.Object {
  val activeTintColor: U[String] = undefined
  val showIcon: U[Boolean] = undefined
}

object TabBarOptions {
  @inline
  def apply(activeTintColor: OP[String] = NoValue,
            showIcon: OP[Boolean] = NoValue): TabBarOptions = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[TabBarOptions]
  }
}

trait TabViewConfig extends js.Object {
  val tabBarComponent: U[ReactClass] = undefined
  val tabBarPosition: U[TabBarPosition] = undefined
  val tabBarOptions: U[js.Object] = undefined
  val swipeEnabled: U[Boolean] = undefined
  val animationEnabled: U[Boolean] = undefined
  val lazyLoad: U[Boolean] = undefined
}

object TabViewConfig {
  @inline
  def apply(tabBarComponent: OP[ReactClass] = NoValue,
            tabBarPosition: OP[TabBarPosition] = NoValue,
            tabBarOptions: OP[js.Object] = NoValue,
            swipeEnabled: OP[Boolean] = NoValue,
            animationEnabled: OP[Boolean] = NoValue,
            lazyLoad: OP[Boolean] = NoValue): TabViewConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[TabViewConfig]
  }
}

trait DrawerPosition extends js.Object

object DrawerPosition {

  @inline def LEFT = "left".asInstanceOf[DrawerPosition]
  @inline def RIGHT = "right".asInstanceOf[DrawerPosition]
}

trait DrawerContentOptions extends js.Object {
  val activeTintColor: U[String] = undefined
  val activeBackgroundColor: U[String] = undefined
  val inactiveTintColor: U[String] = undefined
  val inactiveBackgroundColor: U[String] = undefined
  val style: U[js.Any] = undefined
}

object DrawerContentOptions {
  @inline
  def apply(activeTintColor: OP[String] = NoValue,
            activeBackgroundColor: OP[String] = NoValue,
            inactiveTintColor: OP[String] = NoValue,
            inactiveBackgroundColor: OP[String] = NoValue,
            style: OP[js.Any] = NoValue): DrawerContentOptions = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[DrawerContentOptions]
  }
}

trait DrawerViewConfig extends js.Object {
  val drawerWidth: U[Double] = undefined
  val drawerPosition: U[DrawerPosition] = undefined
  val contentComponent: U[ReactClass] = undefined
  val contentOptions: U[js.Object] = undefined
  val style: U[js.Any] = undefined
}

object DrawerViewConfig {
  @inline
  def apply(drawerWidth: OP[Double] = NoValue,
            drawerPosition: OP[DrawerPosition] = NoValue,
            contentComponent: OP[ReactClass] = NoValue,
            contentOptions: OP[js.Object] = NoValue,
            style: OP[js.Any] = NoValue): DrawerViewConfig = {
    val p = FunctionObjectMacro()
    p.asInstanceOf[DrawerViewConfig]
  }
}

trait NavigationScreenDetails[T <: js.Object] extends js.Object {
  val options: T
  val state: NavigationRoute[js.Object]
  val navigation: Navigation[js.Object]
}

trait HeaderProps extends NavigationTransitionProps {
  val mode: HeaderMode
  val router: NavigationRouter
  val getScreenDetails: js.Function1[
    NavigationScene,
    NavigationScreenDetails[NavigationStackScreenOptions]]
}
