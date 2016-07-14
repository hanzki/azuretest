package controllers

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.db.NamedDatabase
import slick.driver.JdbcProfile

case class Entity(
                 id: Int,
                 value: String
                 )
/**
  * Created by hanzki on 13/07/16.
  */
abstract class DatabaseDao extends HasDatabaseConfigProvider[JdbcProfile] with EntityTable {
  import driver.api._

  val entities = TableQuery[Entities]

  def insert(e: Entity) = db.run(entities += e)

  def get(id: Int) = db.run(entities.filter(_.id === id).result)

  def all = db.run(entities.result)

}

class H2DatabaseDao @Inject() (@NamedDatabase("default") protected val dbConfigProvider: DatabaseConfigProvider) extends DatabaseDao

class AzureDatabaseDao @Inject() (@NamedDatabase("azure") protected val dbConfigProvider: DatabaseConfigProvider) extends DatabaseDao
