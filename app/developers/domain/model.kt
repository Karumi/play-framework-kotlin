package developers.domain

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
}