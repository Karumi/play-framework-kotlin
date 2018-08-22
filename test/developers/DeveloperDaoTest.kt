package developers

import developers.storage.DeveloperDao
import given.GivenDeveloper
import given.givenDeveloper
import junit.framework.TestCase.assertEquals
import org.junit.Test
import utils.ApplicationWithDatabase
import utils.getOrNull

class DeveloperDaoTest : ApplicationWithDatabase(), GivenDeveloper by givenDeveloper {

  val dao = DeveloperDao()

  @Test
  fun `developer should be updated`() {
    val developer = givenDeveloper()
    dao.create(developer)

    val developerUpdate = developer.copy(username = "Pedro")
    val updatedDeveloper = dao.update(developerUpdate).getOrNull()
    val obtainedDeveloper = dao.getById(developer.id).getOrNull()?.getOrNull()

    assertEquals(developerUpdate, updatedDeveloper)
    assertEquals(developerUpdate, obtainedDeveloper)
  }
}