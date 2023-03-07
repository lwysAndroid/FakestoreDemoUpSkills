package com.chemasmas.fakestoreapi.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MakeLoginUseCase @Inject constructor() {

    operator fun invoke(email: String, password: String): Flow<Boolean> = flow {
        emit(true)
    }

}