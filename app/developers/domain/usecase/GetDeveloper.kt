package developers.domain.usecase

import arrow.core.Either
import arrow.core.Option
import developers.domain.Developer
import developers.domain.DeveloperError
import developers.domain.DeveloperError.Companion.toStorageError
import developers.storage.DeveloperDao
import java.util.UUID
import javax.inject.Inject

class GetDeveloper @Inject constructor(
  private val developerDao: DeveloperDao
) {

  operator fun invoke(developerId: UUID): Either<DeveloperError, Developer> =
    developerDao.getById(developerId).fold(
      ifFailure = ::toStorageError,
      ifSuccess = this::toEither
    )

  private fun toEither(developerOption: Option<Developer>): Either<DeveloperError, Developer> =
    developerOption.toEither { DeveloperError.NotFound }
}