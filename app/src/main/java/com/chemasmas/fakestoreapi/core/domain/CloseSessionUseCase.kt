package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.CloseSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CloseSessionUseCase @Inject constructor(
    private val closeSessionRepository: CloseSessionRepository,
) {
    fun execute(): Flow<Boolean> {
        return closeSessionRepository.closeSession()
    }
}