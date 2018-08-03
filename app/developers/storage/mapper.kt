package developers.storage

import developers.Developer

fun Developer.toEntity(): DeveloperEntity =
  DeveloperEntity().also { entity ->
    entity.id = id
    entity.username = username
    entity.email = email
  }

fun DeveloperEntity.toDomain(): Developer = Developer(
  id = id,
  username = username,
  email = email
)