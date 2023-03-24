package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.CloseSessionRepository
import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CloseSessionRepositoryImp @Inject constructor(
    private val tokensRepository: TokensRepository,
) : CloseSessionRepository {

    override fun closeSession(): Flow<Boolean> = flow {
        tokensRepository.setRefreshToken("")
        tokensRepository.setAccessToken("")
        emit(true)
    }

}