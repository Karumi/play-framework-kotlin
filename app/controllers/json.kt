package controllers

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import play.mvc.Controller
import play.mvc.Http
import play.mvc.Result
import play.mvc.Results.badRequest
import java.util.concurrent.CompletionStage

interface ParseableJson {
  fun <A> A.toJson(): JsonNode {
    val mapper = jacksonObjectMapper()
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    return mapper.valueToTree(this)
  }
}

inline fun <reified A> Controller.readAsyncJsonBody(
  crossinline success: (A) -> Result
): CompletionStage<Result> = readJsonBody<A> {
  async { success(it) }
}

inline fun <reified A> Controller.readJsonBody(
  success: (A) -> CompletionStage<Result>
): CompletionStage<Result> = try {
  success(Controller.request().readJsonBody())
} catch (ex: Throwable) {
  badRequest("Json bad formed").completeFuture()
}

inline fun <reified A> Http.Request.readJsonBody(): A {
  val mapper = jacksonObjectMapper()
  return mapper.readValue(body().asJson().toString(), A::class.java)
}