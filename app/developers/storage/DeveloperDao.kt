package developers.storage

import developers.domain.Developer
import java.util.UUID

class DeveloperDao {

  fun create(developer: Developer): Developer {
    developer.toEntity().save()
    return developer
  }

  fun update(developer: Developer): Developer {
    developer.toEntity().update()
    return developer
  }

  fun getById(id: UUID): Developer? =
    DeveloperEntity.DAO
      .query()
      .where()
      .idEq(id)
      .findOne()
      ?.toDomain()
}