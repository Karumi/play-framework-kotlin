package developers.domain.usecase

import developers.domain.Developer
import developers.domain.DeveloperValidator
import developers.storage.DeveloperDao
import javax.inject.Inject

class CreateKarumiDeveloper @Inject constructor(
  private val developerDao: DeveloperDao
) {

  operator fun invoke(developer: Developer): Developer? =
    if (DeveloperValidator.isKarumiDeveloper(developer)) {
      developerDao.create(developer)
    } else {
      null
    }

}