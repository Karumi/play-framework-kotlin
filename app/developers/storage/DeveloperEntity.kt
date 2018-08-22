package developers.storage

import io.ebean.Finder
import io.ebean.Model
import io.ebean.annotation.CreatedTimestamp
import io.ebean.annotation.UpdatedTimestamp
import java.sql.Timestamp
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "developer")
class DeveloperEntity : Model() {

  companion object {
    val DAO = Finder<UUID, DeveloperEntity>(DeveloperEntity::class.java)
  }

  @Id
  @GeneratedValue
  lateinit var id: UUID

  lateinit var username: String
  var email: String? = null
}