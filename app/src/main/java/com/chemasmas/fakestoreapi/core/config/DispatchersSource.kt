package com.chemasmas.fakestoreapi.core.config

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersSource {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}