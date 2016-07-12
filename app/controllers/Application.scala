package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}


class Application @Inject() extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hello(name: Option[String]) = Action {
    Ok(s"hello ${name.getOrElse("world!")}")
  }

}
