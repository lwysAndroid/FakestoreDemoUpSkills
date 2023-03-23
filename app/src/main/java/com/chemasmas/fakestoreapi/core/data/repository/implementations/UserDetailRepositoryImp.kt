package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import com.chemasmas.fakestoreapi.core.data.repository.UserDetailRepository
import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDetailRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.UserDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailRepositoryImp @Inject constructor(
    private val upskillsNetworkDataSource: UpskillsNetworkDataSource,
    private val tokensRepository: TokensRepository,
) : UserDetailRepository {

    override fun getUserDetail(userId: Int): Flow<UserDetailResponse> = flow {
        val response =
            upskillsNetworkDataSource.getUserDetail(
                accessToken = tokensRepository.getBearerAccessToken(),
                userDetailRequest = UserDetailRequest(id = userId)
            )
        emit(response)
    }

}