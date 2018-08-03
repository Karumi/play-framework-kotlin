package developers.storage

import arrow.core.Option
import arrow.core.Try
import arrow.core.toOption
import developers.domain.Developer
import java.util.UUID

class DeveloperDao {

  fun create(developer: Developer): Try<Developer> = Try {
    developer.toEntity().save()
    developer
  }

  fun update(developer: Developer): Try<Developer> = Try {
    developer.toEntity().update()
    developer
  }

  fun getById(id: UUID): Try<Option<Developer>> = Try {
    DeveloperEntity.DAO
      .query()
      .where()
      .idEq(id)
      .findOne()
      ?.toDomain()
      .toOption()
  }

}