package controllers

import play.mvc.Controller
import play.mvc.Result

class Application : Controller() {

  fun index(): Result = ok("Your new application is ready.")
}