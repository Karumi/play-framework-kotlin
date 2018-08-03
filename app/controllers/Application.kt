package controllers

import developers.NewDeveloperJson
import developers.domain.usecase.CreateKarumiDeveloper
import developers.domain.Developer
import developers.domain.usecase.GetDeveloper
import developers.toDomain
import play.mvc.Controller
import play.mvc.Result
import java.util.UUID
import java.util.concurrent.CompletionStage
import javax.inject.Inject

class Application @Inject constructor(
  private val createKarumiDeveloper: CreateKarumiDeveloper,
  private val getDeveloper: GetDeveloper
) : Controller(), ParseableJson {

  fun index(): Result = ok("Your new application is ready.")

  fun createDeveloper(): CompletionStage<Result> = readAsyncJsonBody<NewDeveloperJson> {
    try {
      val developer = createKarumiDeveloper(it.toDomain())
      if(developer != null) {
        created(developer)
      } else {
        badRequest("Something went wrong")
      }
    } catch (ex: Throwable) {
      processError(ex)
    }
  }

  fun getDeveloper(developerId: String): CompletionStage<Result> = async {
    try {
      val developer = getDeveloper(UUID.fromString(developerId))
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