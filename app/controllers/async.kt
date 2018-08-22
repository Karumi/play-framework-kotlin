package controllers

import play.mvc.Result
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

fun async(complete: () -> Result): CompletionStage<Result> =
  CompletableFuture.supplyAsync(complete)

fun Result.completeFuture(): CompletionStage<Result> =
  CompletableFuture.completedFuture(this)

