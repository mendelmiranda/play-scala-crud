package models

import anorm._
import anorm.SqlParser._
import org.joda.time.DateTime
import play.api.db.DB
import play.api.Play.current

case class Client(id: Long, name: String, birth: java.util.Date, email: String)

object Client {
  val sql: SqlQuery = SQL("SELECT * from Client ORDER BY name asc")


  val defaultParser = {
    int("id") ~
      str("name") ~
      get[java.util.Date]("birth") ~
      str("email") map {
      case id ~ name ~ birth ~ email => Client(id, name, birth, email)
    }
  }

  def all(): List[Client] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM Client ORDER BY name").as(defaultParser *)
  }

  def load(id: Long) = {
    DB.withConnection { implicit c =>
      SQL("SELECT * FROM Client WHERE id={id};")
        .on('id -> id).executeQuery().singleOpt(defaultParser)
    }
  }

  def create(client: Client) = {
    DB.withConnection { implicit c =>
      SQL("INSERT INTO Client (name,birth,email) VALUES ({name},{birth},{email});")
        .on( 'name -> client.name, 'birth -> client.birth, 'email -> client.email).executeInsert()
    }
  }

  def update(id: Long, name: String, birth: java.util.Date, email: String) = {
    DB.withConnection { implicit c =>
      SQL("UPDATE Client SET name = {name}, birth = {birth}, email = {email} WHERE id = {id}")
        .on('name -> name, 'birth -> birth, 'email -> email, 'id -> id).executeUpdate()
    }
  }

  def delete(id: Long) = {
    DB.withConnection { implicit c =>
      SQL("DELETE FROM Client WHERE id={id};")
        .on('id -> id).executeUpdate()
    }
    all()
  }


}