package developers

import arrow.core.getOrElse
import developers.storage.DeveloperDao
import given.GivenDeveloper
import given.givenDeveloper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import utils.ApplicationWithDatabase

class DeveloperDaoTest : ApplicationWithDatabase(), GivenDeveloper by givenDeveloper {

  val dao = DeveloperDao()

  @Test
  fun `developer should be updated`() {
    val developer = givenDeveloper()
    dao.create(developer)

    val developerUpdate = developer.copy(username = "Pedro")
    val updatedDeveloper = dao.update(developerUpdate).get()
    val obtainedDeveloper = dao.getById(developer.id).get().get()

    assertEquals(developerUpdate, updatedDeveloper)
    assertEquals(developerUpdate, obtainedDeveloper)
  }

}