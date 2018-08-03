package given

import developers.NewDeveloperJson
import developers.domain.Developer
import java.util.UUID

interface GivenDeveloper {
  fun givenDeveloper(): Developer
  fun givenNewDeveloper(): NewDeveloperJson
  fun givenNewKarumiDeveloper(): NewDeveloperJson
}

val givenDeveloper = GivenDummyDevelopers()

class GivenDummyDevelopers : GivenDeveloper {

  override fun givenDeveloper(): Developer = Developer(
    id = UUID.randomUUID(),
    username = "Unknown",
    email = null
  )

  override fun givenNewDeveloper(): NewDeveloperJson = NewDeveloperJson(
    username = "Unknown",
    email = "email@email.com"
  )

  override fun givenNewKarumiDeveloper(): NewDeveloperJson = NewDeveloperJson(
    username = "Unknown",
    email = "email@karumi.com"
  )
}