package developers.domain

import java.util.UUID

data class Developer(
  val id: UUID,
  val username: String,
  val email: String?
)