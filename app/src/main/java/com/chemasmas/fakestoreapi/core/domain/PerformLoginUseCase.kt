package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.PerformLoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PerformLoginUseCase @Inject constructor(
    private val performLoginRepository: PerformLoginRepository,
) {
    fun execute(email: String, password: String): Flow<Boolean> {
        return performLoginRepository.performLogin(email = email, password = password)
    }
}