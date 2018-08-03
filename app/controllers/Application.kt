package controllers

import developers.Developer
import developers.NewDeveloperJson
import developers.storage.DeveloperDao
import developers.toDomain
import play.mvc.Controller
import play.mvc.Result
import java.util.UUID
import java.util.concurrent.CompletionStage
import javax.inject.Inject

class Application @Inject constructor(
  private val developerDao: DeveloperDao
) : Controller(), ParseableJson {

  fun index(): Result = ok("Your new application is ready.")

  fun createDeveloper(): CompletionStage<Result> = readAsyncJsonBody<NewDeveloperJson> {
    try {
      created(developerDao.create(it.toDomain()))
    } catch (ex: Throwable) {
      processError(ex)
    }
  }

  fun getDeveloper(developerId: String): CompletionStage<Result> = async {
    try {
      val developer = developerDao.getById(UUID.fromString(developerId))
      if (developer != null) {
        ok(developer)
      } else {
        notFound()
      }
    } catch (ex: Throwable) {
      processError(ex)
    }
  }

  fun processError(error: Throwable): Result = badRequest()
  fun created(developer: Developer): Result = created(developer.toJson())
  fun ok(developer: Developer): Result = ok(developer.toJson())
}