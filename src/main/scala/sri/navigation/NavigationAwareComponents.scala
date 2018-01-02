package sri.navigation

import sri.core.{=:!=, Component, ComponentConstructor, ComponentJS, ComponentNoPS, ComponentP, ComponentS, CreateElement, InternalComponentP, React, ReactClass, ReactScalaClass}
import sri.relay.{CreateRelayElement, RelayContainer, RelayFragmentClass}

import scala.language.existentials
import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.ConstructorTag
import scala.scalajs.js.annotation.JSExportStatic

trait ScreenClass extends ReactClass {
  type ParamsType <: js.Object
}

abstract class NavigationScreenComponentP[P >: Null <: js.Object](
    implicit ev: =:!=[P, Null])
    extends NavigationScreenComponent[P, Null] {}

abstract class NavigationScreenComponentNoPS
    extends NavigationScreenComponent[Null, Null]

abstract class NavigationScreenComponentS[S <: AnyRef](
    implicit ev: =:!=[S, Null])
    extends NavigationScreenComponent[Null, S]

abstract class NavigationScreenComponent[Params >: Nothing <: js.Object,
S <: AnyRef](implicit ev: =:!=[Params, Nothing])
    extends ComponentJS[NavigatorScreenProps[Params], S]
    with ScreenClass {

  override type ParamsType = Params

  implicit def navigationJS = props.navigation
  def navigation = NavigationCtrl

  @inline
  def params: js.UndefOr[ParamsType] = props.navigation.state.params

  @inline
  def setParams(params: ParamsType) = props.navigation.setParams(params)

}

object NavigationCtrl {

  type NV = Navigation[_]

  @inline
  def navigate[C <: NavigationScreenComponent[Null, _]: ConstructorTag](
      implicit ctag: ClassTag[C],
      navigation: NV) =
    navigation.navigate(ctag.runtimeClass.getName)

  @inline
  def navigate[C <: ScreenClass: ConstructorTag](
      params: C#ParamsType)(implicit ctag: ClassTag[C], navigation: NV) =
    navigation
      .navigate(ctag.runtimeClass.getName, params)
  @inline
  def goBack(routeKey: String)(implicit navigation: NV) =
    navigation.goBack(routeKey)

  @inline
  def goBack()(implicit navigation: NV) = navigation.goBack()

  @inline
  def openDrawer()(implicit navigation: NV) = navigation.navigate(DRAWER_OPEN)

  @inline
  def closeDrawer()(implicit navigation: NV) =
    navigation.navigate(DRAWER_CLOSE)

}

sealed trait NavigationAwareComponentClass extends ReactScalaClass {
  override type ScalaPropsType <: AnyRef
}

abstract class NavigationAwareComponent[
    P >: Null <: AnyRef, S >: Null <: AnyRef]
    extends Component[P, S]
    with NavigationAwareComponentClass {

  @inline
  implicit def navigationJS: Navigation[_] =
    jsProps.asInstanceOf[js.Dynamic].navigation.asInstanceOf[Navigation[_]]

  def navigation = NavigationCtrl

}

abstract class NavigationAwareComponentP[P >: Null <: AnyRef]
    extends ComponentP[P]
    with NavigationAwareComponentClass {

  @inline
  implicit def navigationJS: Navigation[_] =
    jsProps.asInstanceOf[js.Dynamic].navigation.asInstanceOf[Navigation[_]]

  def navigation = NavigationCtrl
}

abstract class NavigationAwareComponentS[S >: Null <: AnyRef]
    extends ComponentS[S]
    with NavigationAwareComponentClass {

  @inline
  def navigationJS: Navigation[_] =
    jsProps.asInstanceOf[js.Dynamic].navigation.asInstanceOf[Navigation[_]]

  def navigation = NavigationCtrl
}

abstract class NavigationAwareComponentNoPS
    extends ComponentNoPS
    with NavigationAwareComponentClass {

  @inline
  def navigationJS: Navigation[_] =
    jsProps.asInstanceOf[js.Dynamic].navigation.asInstanceOf[Navigation[_]]

  def navigation = NavigationCtrl
}

abstract class RouterAwareComponentJS[
    P >: Null <: js.Object, S >: Null <: AnyRef]
    extends ComponentJS[P, S] {

  @inline
  implicit def navigationJS: Navigation[_] =
    props.asInstanceOf[js.Dynamic].navigation.asInstanceOf[Navigation[_]]
  def navigation = NavigationCtrl
}

class WithNavigation extends ComponentP[WithNavigation.Props] {

  def render() = {
    React.createElement(props.ctor,
                        js.Dynamic.literal(scalaProps =
                                             props.cProps.asInstanceOf[js.Any],
                                           navigation = context.navigation))
  }
}

object WithNavigation {

  @JSExportStatic
  val contextTypes = navigationContextType

  case class Props(ctor: js.Any, cProps: Any)

  @inline
  def apply[C <: NavigationAwareComponentClass {
    type ScalaPropsType >: Null <: AnyRef
  }: js.ConstructorTag](props: C#ScalaPropsType) = {
    val ctor = js.constructorTag[C].constructor
    CreateElement[WithNavigation](Props(ctor, props))
  }

  @inline
  def apply[C <: NavigationAwareComponentClass { type ScalaPropsType = Null }: js.ConstructorTag]() = {
    val ctor = js.constructorTag[C].constructor
    CreateElement[WithNavigation](Props(ctor, null))
  }
}

class WithRelayNavigation extends ComponentP[WithRelayNavigation.Props] {

  def render() = {
    React.createElement(props.ctor,
      js.Dynamic.literal(scalaProps =
        props.cProps.asInstanceOf[js.Any],
        navigation = context.navigation))
  }
}

object WithRelayNavigation {


  @JSExportStatic
  val contextTypes = navigationContextType

  case class Props(ctor: js.Any, cProps: Any)

  @inline
  def apply[C <: RelayFragmentClass {
    type PropsType >: Null <: AnyRef
  }: js.ConstructorTag](container: RelayContainer[C],
                        props: C#PropsType) = {
    val ctor = js.constructorTag[C].constructor
    CreateRelayElement[C](container, props)
  }

  // TODO change this
  @inline
  def apply[C <: RelayFragmentClass { type PropsType = Null }: js.ConstructorTag]() = {
    val ctor = js.constructorTag[C].constructor
    CreateElement[WithRelayNavigation](Props(ctor, null))
  }
}

trait NavigatorViewComponentProps extends js.Object {
  val router: NavigationRouter
  val navigation: Navigation[js.Object]
}

trait NavigatorViewComponentClass extends ReactClass

trait NavigationNavigatorComponentConstructor extends ComponentConstructor

abstract class NavigatorViewComponent[P >: Null <: NavigatorViewComponentProps,
                                      S >: Null <: AnyRef](
    implicit ev: =:!=[P, Null],
    ev2: =:!=[S, Null])
    extends ComponentJS[P, S]
    with NavigatorViewComponentClass {

  @inline
  def router: NavigationRouter = props.router

  @inline
  implicit def navigationJS = props.navigation.asInstanceOf[Navigation[_]]

  @inline
  def navigation = NavigationCtrl
}

abstract class NavigatorViewComponentP[
    P >: Null <: NavigatorViewComponentProps](implicit ev: =:!=[P, Null])
    extends ComponentJS[P, Null]
    with NavigatorViewComponentClass {
  @inline
  def router: NavigationRouter = props.router

  @inline
  implicit def navigationJS = props.navigation.asInstanceOf[Navigation[_]]

  @inline
  def navigation = NavigationCtrl
}
