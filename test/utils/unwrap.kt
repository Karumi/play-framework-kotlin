package utils

import arrow.core.Option
import arrow.core.Try
import arrow.core.getOrElse

fun <A> Option<A>.getOrNull(): A? = getOrElse { null }
fun <A> Try<A>.getOrNull(): A? = getOrElse { null }