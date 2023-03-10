package com.chemasmas.fakestoreapi.core.domain

import androidx.core.util.PatternsCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    fun execute(email: String): Flow<Boolean> = flow {
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
            .also { emit(it) }
    }

}