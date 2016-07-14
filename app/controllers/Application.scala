package controllers

import javax.inject.Inject

import play.api.{Logger, Configuration}
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global


class Application @Inject() (h2Dao: H2DatabaseDao, azDao: AzureDatabaseDao, configuration: Configuration) extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hello(name: Option[String]) = Action {
    Logger.error("error")
    Logger.warn("warn")
    Logger.info("info")
    Logger.debug("debug")
    val default = configuration.getString("hello.default").get
    Ok(s"hello ${name.getOrElse(default)}")
  }

  def insert(id: Int, v: String) = Action.async {
    h2Dao.insert(Entity(id, v)).map(a => Ok(a.toString))
  }

  def get(id: Int) = Action.async {
    h2Dao.get(id).map(a => Ok(a.toString))
  }

  def list = Action.async {
    h2Dao.all.map(a => Ok(a.toString))
  }

  def azureInsert(id: Int, v: String) = Action.async {
    azDao.insert(Entity(id, v)).map(a => Ok(a.toString))
  }

  def azureGet(id: Int) = Action.async {
    azDao.get(id).map(a => Ok(a.toString))
  }

  def azureList = Action.async {
    azDao.all.map(a => Ok(a.toString))
  }

}
