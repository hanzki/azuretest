package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global


class Application @Inject() (dao: DatabaseDao) extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hello(name: Option[String]) = Action {
    Ok(s"hello ${name.getOrElse("world!")}")
  }

  def insert(id: Int, v: String) = Action.async {
    dao.insert(Entity(id, v)).map(a => Ok(a.toString))
  }

  def get(id: Int) = Action.async {
    dao.get(id).map(a => Ok(a.toString))
  }

  def list = Action.async {
    dao.all.map(a => Ok(a.toString))
  }

}
