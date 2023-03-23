package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.PerformLoginRepository
import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDataLoginRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PerformLoginRepositoryImp @Inject constructor(
    private val upskillsNetworkDataSource: UpskillsNetworkDataSource,
    private val tokensRepository: TokensRepository,
) : PerformLoginRepository {

    override fun performLogin(email: String, password: String): Flow<Boolean> = flow {
        val response = upskillsNetworkDataSource.performLogin(
            userDataLoginRequest = UserDataLoginRequest(
                username = email,
                password = password
            )
        )
        tokensRepository.setAccessToken(response.access)
        tokensRepository.setRefreshToken(response.refresh)
        emit(true)
    }

}