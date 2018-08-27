package developers.domain.usecase

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import developers.domain.Developer
import developers.domain.DeveloperError
import developers.domain.DeveloperValidator
import developers.storage.DeveloperDao
import javax.inject.Inject

class CreateKarumiDeveloper @Inject constructor(
  private val developerDao: DeveloperDao
) {

  operator fun invoke(developer: Developer): Either<DeveloperError, Developer> =
    validKarumiDeveloper(developer)
      .flatMap {
        developerDao.create(it)
          .toEither()
          .mapLeft { DeveloperError.StorageError }
      }

  private fun validKarumiDeveloper(developer: Developer): Either<DeveloperError, Developer> =
    if (DeveloperValidator.isKarumiDeveloper(developer)) {
      developer.right()
    } else {
      DeveloperError.NotKarumier.left()
    }
}