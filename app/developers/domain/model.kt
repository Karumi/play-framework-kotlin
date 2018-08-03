package developers.domain

import arrow.core.Either
import arrow.core.left
import java.util.UUID

data class Developer(
  val id: UUID,
  val username: String,
  val email: String?
)

sealed class DeveloperError {
  object StorageError : DeveloperError()
  object NotFound : DeveloperError()
  object NotKarumier : DeveloperError()

  companion object {
    fun toStorageError(ex: Throwable): Either<DeveloperError, Developer> =
      DeveloperError.StorageError.left()
  }
}