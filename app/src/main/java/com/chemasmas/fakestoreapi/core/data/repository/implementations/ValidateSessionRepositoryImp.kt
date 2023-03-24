package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import com.chemasmas.fakestoreapi.core.data.repository.ValidateSessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateSessionRepositoryImp @Inject constructor(
    private val tokensRepository: TokensRepository,
) : ValidateSessionRepository {

    override fun validateSession(): Flow<Boolean> = flow {
        tokensRepository.getAccessToken()
            .also {
                emit(!it.isNullOrEmpty())
            }
    }

}