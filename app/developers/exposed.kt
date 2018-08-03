package developers

import developers.domain.Developer
import java.util.UUID

data class NewDeveloperJson(
  val username: String,
  val email: String?
)

fun NewDeveloperJson.toDomain(): Developer = Developer(
  id = UUID.randomUUID(),
  username = username,
  email = email
)