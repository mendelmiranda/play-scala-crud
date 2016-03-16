package controllers

import java.lang.ProcessBuilder.Redirect

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import play.api.cache.Cached

import play.api.Play.current
import play.api.i18n.Messages.Implicits._

import anorm.NotAssigned

class Clients extends Controller {

  val form = Form(
    tuple(
      "name" -> text,
      "birth" -> date,
      "email" -> nonEmptyText
    )
  )

  def listClients = Client.all

  def newClient = Action { implicit request =>
    Ok(views.html.newclient(form))
  }


  def todos = Action { implicit request =>
    Ok(views.html.bootstrap(listClients,form))
  }

  def create() = Action { implicit request =>

    form.bindFromRequest.fold(
    formWithErrors => {
      BadRequest(views.html.newclient(formWithErrors))}
    ,
      cli => {
      val (name, birth, email) = cli

      val client = new Client(0,name,birth,email)

      Client.create(client)
      Redirect(routes.Clients.todos()).flashing("success" -> "Client saved!")
    })
  }

  def edit(id: Long) = Action { implicit request =>
    Client.load(id).map { client =>
      Ok(views.html.editclient(client))
    }.getOrElse(NotFound)
  }

  def update( id: Long ) = Action { implicit request =>

    form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.newclient(formWithErrors))}
      ,
      cli => {

        val (name, birth, email) = cli

        Client.update(id,name,birth,email)
        Redirect(routes.Clients.todos()).flashing("success" -> "Client updated!")
      }
    )
  }

  def delete(id: Long) = Action { implicit request =>
        Client.delete(id)
        Redirect(routes.Clients.todos()).flashing("success" -> "Client Removed!")
  }

}
