package developers.domain.usecase

import arrow.core.Either
import arrow.core.left
import developers.domain.Developer
import developers.domain.DeveloperError
import developers.storage.DeveloperDao
import java.util.UUID
import javax.inject.Inject

class GetDeveloper @Inject constructor(
  private val developerDao: DeveloperDao
) {

  operator fun invoke(developerId: UUID): Either<DeveloperError, Developer> =
    developerDao.getById(developerId).fold(
      ifFailure = { DeveloperError.StorageError.left() },
      ifSuccess = { it.toEither { DeveloperError.NotFound } }
    )
}