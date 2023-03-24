package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.RefreshTokenRepository
import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.requests.RefreshTokenRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.RefreshTokenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshTokenRepositoryImp @Inject constructor(
    private val upskillsNetworkDataSource: UpskillsNetworkDataSource,
    private val tokensRepository: TokensRepository,
) : RefreshTokenRepository {

    override suspend fun refreshToken(): Flow<RefreshTokenResponse> = flow {
        val response =
            upskillsNetworkDataSource.refreshToken(
                accessToken = tokensRepository.getBearerAccessToken(),
                refreshTokenRequest = RefreshTokenRequest(refresh = tokensRepository.getRefreshToken())
            )
        tokensRepository.setAccessToken(response.access)
        emit(response)
    }

}