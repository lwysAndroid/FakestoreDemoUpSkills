package com.chemasmas.fakestoreapi.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateUserNameUseCase @Inject constructor() {
    fun execute(userName: String): Flow<Boolean> = flow {
        val isValid = userName.isNotEmpty() && userName.length >= 2
        emit(isValid)
    }
}