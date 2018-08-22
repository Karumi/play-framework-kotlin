package developers.domain.usecase

import developers.domain.Developer
import developers.storage.DeveloperDao
import java.util.UUID
import javax.inject.Inject

class GetDeveloper @Inject constructor(
  private val developerDao: DeveloperDao
) {

  operator fun invoke(developerId: UUID): Developer? = developerDao.getById(developerId)

}