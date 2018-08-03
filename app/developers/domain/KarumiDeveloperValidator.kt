package developers.domain

class KarumiDeveloperValidator {

  companion object {
    private const val KARUMI_EMAIL = "@karumi.com"
  }

  fun isKarumiDeveloper(developer: Developer): Boolean =
    developer.email != null && developer.email.contains(KARUMI_EMAIL)

}