package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.ValidateSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidateSessionUseCase @Inject constructor(
    private val validateSessionRepository: ValidateSessionRepository,
) {
    fun execute(): Flow<Boolean> {
        return validateSessionRepository.validateSession()
    }
}