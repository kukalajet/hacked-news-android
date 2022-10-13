package com.jeton.hackednews.core.utils

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun <T, V> Iterable<T>.asyncMap(coroutine: suspend (T) -> V): Iterable<V> =
    coroutineScope {
        this@asyncMap.map { async { coroutine(it) } }.awaitAll()
    }