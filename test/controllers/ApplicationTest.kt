package controllers

import developers.NewDeveloperJson
import developers.domain.Developer
import developers.storage.DeveloperDao
import given.GivenDeveloper
import given.givenDeveloper
import junit.framework.Assert.assertEquals
import org.junit.Test
import play.mvc.Http.Status.BAD_REQUEST
import play.mvc.Http.Status.CREATED
import play.mvc.Http.Status.NOT_FOUND
import play.mvc.Http.Status.OK
import play.mvc.Result
import play.test.Helpers.fakeRequest
import play.test.Helpers.route
import utils.ApplicationWithDatabase
import utils.asObject
import java.util.UUID

class ApplicationTest : ApplicationWithDatabase(), ParseableJson, GivenDeveloper by givenDeveloper {

  val dao = DeveloperDao()

  @Test
  fun `developer POST should create a developer if it's a karumi developer`() {
    val newDeveloper = givenNewKarumiDeveloper()

    val result = postDeveloperRoute(newDeveloper)

    val createdDeveloper = result.asObject(Developer::class)
    val developer = Developer(createdDeveloper.id, newDeveloper.username, newDeveloper.email)

    assertEquals(developer, createdDeveloper)
    assertEquals(developer, getById(developer.id))
    assertEquals(CREATED, result.status())
  }

  @Test
  fun `developer POST shouldn't create a developer if it isn't a karumi developer`() {
    val newDeveloper = givenNewDeveloper()

    val result = postDeveloperRoute(newDeveloper)

    assertEquals(BAD_REQUEST, result.status())
  }

  @Test
  fun `developer POST should returns 400 if the json body isn't the expected`() {
    val result = postDeveloperRoute(InvalidJson())

    assertEquals(BAD_REQUEST, result.status())
  }

  @Test
  fun `developer GET should retrieve by id`() {
    val developer = givenDeveloper().let { create(it) }

    val result = getDeveloperRoute(developer)

    assertEquals(developer, result.asObject(Developer::class))
    assertEquals(OK, result.status())
  }

  @Test
  fun `developer GET should returns 404 code if there isn't the developer in the database`() {
    val developer = givenDeveloper()

    val result = getDeveloperRoute(developer)

    assertEquals(NOT_FOUND, result.status())
  }

  private fun getDeveloperRoute(developer: Developer): Result =
    route(app, fakeRequest("GET", "/developer/${developer.id}"))

  private fun <A> postDeveloperRoute(body: A): Result =
    route(app, fakeRequest("POST", "/developer").bodyJson(body.toJson()))

  private data class InvalidJson(val invalid: String = "")
  private fun getById(id: UUID) = dao.getById(id)
  private fun create(developer: Developer) = dao.create(developer)
}