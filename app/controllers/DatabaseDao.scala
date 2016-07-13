package controllers

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

case class Entity(
                 id: Int,
                 value: String
                 )
/**
  * Created by hanzki on 13/07/16.
  */
class DatabaseDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] with EntityTable {
  import driver.api._

  val entities = TableQuery[Entities]

  def insert(e: Entity) = db.run(entities += e)

  def get(id: Int) = db.run(entities.filter(_.id === id).result)

  def all = db.run(entities.result)

}
