package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class HomeController @Inject()(val cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

  import forms.UserForm._

  private val postUrl = routes.HomeController.user()

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def hello(name: String) = Action {
    Ok(views.html.hello(name))
  }

  def helloUser() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.user(userForm, postUrl))
  }

  def user() = Action { implicit request: MessagesRequest[AnyContent] =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.user(formWithErrors, postUrl))
      },
      userData => {
        Redirect(routes.HomeController.helloUser()).withCookies(new Cookie("userName", userData.name))
      }
    )
  }
}
