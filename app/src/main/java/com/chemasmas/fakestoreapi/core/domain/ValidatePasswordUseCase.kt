package com.chemasmas.fakestoreapi.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    fun execute(password: String): Flow<Boolean> = flow {
        emit(password.isNotEmpty())
    }
}