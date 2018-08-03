package developers.domain.usecase

import arrow.core.Either
import arrow.core.Either.Companion.cond
import arrow.core.Either.Companion.right
import arrow.core.flatMap
import developers.domain.Developer
import developers.domain.DeveloperError
import developers.domain.DeveloperError.Companion.toStorageError
import developers.domain.DeveloperValidator
import developers.storage.DeveloperDao
import javax.inject.Inject

class CreateKarumiDeveloper @Inject constructor(
  private val developerDao: DeveloperDao
) {

  operator fun invoke(developer: Developer): Either<DeveloperError, Developer> =
    validKarumiDeveloper(developer)
      .flatMap {
        developerDao.create(it).fold(
          ifFailure = ::toStorageError,
          ifSuccess = ::right
        )
      }

  private fun validKarumiDeveloper(developer: Developer): Either<DeveloperError, Developer> =
    cond(
      test = DeveloperValidator.isKarumiDeveloper(developer),
      ifTrue = { developer },
      ifFalse = { DeveloperError.NotKarumier }
    )
}