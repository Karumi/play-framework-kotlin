package controllers

import play.mvc.Controller
import play.mvc.Result
import play.mvc.Results

class Application : Controller() {

  fun index(): Result {
    return Results.ok("Your new application is ready.")
  }

}
