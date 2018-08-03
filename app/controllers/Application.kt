package controllers

import developers.NewDeveloperJson
import developers.domain.Developer
import developers.domain.DeveloperError
import developers.domain.usecase.CreateKarumiDeveloper
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
    createKarumiDeveloper(it.toDomain()).fold(
      ifLeft = this::processError,
      ifRight = this::created
    )
  }

  fun getDeveloper(developerId: String): CompletionStage<Result> = async {
    getDeveloper(UUID.fromString(developerId)).fold(
      ifLeft = this::processError,
      ifRight = this::ok
    )
  }

  private fun processError(developerError: DeveloperError): Result = when (developerError) {
    DeveloperError.StorageError -> internalServerError()
    DeveloperError.NotFound -> notFound()
    DeveloperError.NotKarumier -> badRequest("Only karumies")
  }

  private fun created(developer: Developer): Result = created(developer.toJson())
  private fun ok(developer: Developer): Result = ok(developer.toJson())
}