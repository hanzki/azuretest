package controllers

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

/**
  * Created by hanzki on 13/07/16.
  */
trait EntityTable { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class Entities(tag: Tag) extends Table[Entity](tag, "ENTITIES") {
    def id = column[Int]("ID")
    def value = column[String]("VALUE")

    def * = (id, value) <> (Entity.tupled, Entity.unapply)
  }

}
