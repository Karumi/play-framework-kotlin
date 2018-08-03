package controllers

import developers.Developer
import developers.storage.DeveloperDao
import junit.framework.Assert.assertEquals
import org.junit.Test
import play.mvc.Http.Status.BAD_REQUEST
import play.mvc.Http.Status.CREATED
import play.mvc.Http.Status.NOT_FOUND
import play.mvc.Http.Status.OK
import play.test.Helpers.fakeRequest
import play.test.Helpers.route
import utils.ApplicationWithDatabase
import utils.asObject
import java.util.UUID

class ApplicationTest : ApplicationWithDatabase(), ParseableJson {

  val dao = DeveloperDao()

  @Test
  fun `developer POST should create a developer`() {
    val newDeveloper = givenANewDeveloper()

    val result = route(app, fakeRequest("POST", "/developer")
      .bodyJson(newDeveloper.toJson()))

    val createdDeveloper = result.asObject(Developer::class)
    val developer = Developer(createdDeveloper.id, newDeveloper.username, newDeveloper.email)

    assertEquals(developer, createdDeveloper)
    assertEquals(developer, getById(developer.id))
    assertEquals(CREATED, result.status())
  }

  @Test
  fun `developer POST should returns 400 if the json is bad formed`() {
    val result = route(app, fakeRequest("POST", "/developer")
      .bodyJson(InvalidJson().toJson()))

    assertEquals(BAD_REQUEST, result.status())
  }

  @Test
  fun `developer GET should retrieve by id`() {
    val developer = givenADeveloper().let { create(it) }

    val result = route(app, fakeRequest("GET", "/developer/${developer.id}"))

    assertEquals(developer, result.asObject(Developer::class))
    assertEquals(OK, result.status())
  }

  @Test
  fun `developer GET should returns 404 code if there isn't the developer in the database`() {
    val developer = givenADeveloper()

    val result = route(app, fakeRequest("GET", "/developer/${developer.id}"))

    assertEquals(NOT_FOUND, result.status())
  }

  data class InvalidJson(val invalid: String = "")
  fun getById(id: UUID) = dao.getById(id)
  fun create(developer: Developer) = dao.create(developer)
}