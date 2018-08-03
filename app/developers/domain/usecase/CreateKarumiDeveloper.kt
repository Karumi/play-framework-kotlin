package developers.domain.usecase

import developers.domain.Developer
import developers.domain.KarumiDeveloperValidator
import developers.storage.DeveloperDao
import javax.inject.Inject

class CreateKarumiDeveloper @Inject constructor(
  private val developerDao: DeveloperDao,
  private val developerValidator: KarumiDeveloperValidator
) {

  operator fun invoke(developer: Developer): Developer? =
    if (developerValidator.isKarumiDeveloper(developer)) {
      developerDao.create(developer)
    } else {
      null
    }

}