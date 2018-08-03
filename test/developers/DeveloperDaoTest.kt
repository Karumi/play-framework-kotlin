package developers

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
  fun `developer should be created and retrieved by id`() {
    val developer = givenDeveloper()

    val createdDeveloper = dao.create(developer)
    val obtainedDeveloper = dao.getById(developer.id)

    assertEquals(developer, createdDeveloper)
    assertEquals(developer, obtainedDeveloper)
  }

  @Test
  fun `developer should be updated`() {
    val developer = givenDeveloper()
    dao.create(developer)

    val developerUpdate = developer.copy(username = "Pedro")
    val updatedDeveloper = dao.update(developerUpdate)
    val obtainedDeveloper = dao.getById(developer.id)

    assertEquals(developerUpdate, updatedDeveloper)
    assertEquals(developerUpdate, obtainedDeveloper)
  }

  @Test
  fun `developer should be null if there are not developers`() {
    val obtainedDeveloper = dao.getById(givenDeveloper().id)

    assertNull(obtainedDeveloper)
  }
}