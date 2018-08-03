package utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import play.mvc.Result
import play.test.Helpers
import kotlin.reflect.KClass

fun <T : Any> Result.asObject(clazz: KClass<T>): T {
  val mapper = jacksonObjectMapper()
  return mapper.readValue(asString(), clazz.java)
}

fun Result.asString(): String = Helpers.contentAsString(this)