package com.chemasmas.fakestoreapi.presentation.config

import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatchersSourceImp @Inject constructor() : DispatchersSource {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}