package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import com.chemasmas.fakestoreapi.core.data.repository.UserDetailRepository
import com.chemasmas.fakestoreapi.core.data.repository.utils.ValidateInvalidTokenAndRefreshIt
import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDetailRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.UserDetailResponse
import retrofit2.HttpException
import javax.inject.Inject

class UserDetailRepositoryImp @Inject constructor(
    private val upskillsNetworkDataSource: UpskillsNetworkDataSource,
    private val tokensRepository: TokensRepository,
    private val validateInvalidTokenAndRefreshIt: ValidateInvalidTokenAndRefreshIt,
) : UserDetailRepository {

    private var hasUpdatedTheToken = false
    override suspend fun getUserDetail(userId: Int): UserDetailResponse {
        try {
            return upskillsNetworkDataSource.getUserDetail(
                accessToken = tokensRepository.getBearerAccessToken(),
                userDetailRequest = UserDetailRequest(id = userId)
            )
        } catch (exc: HttpException) {
            if (!hasUpdatedTheToken) {
                hasUpdatedTheToken = true
                return validateInvalidTokenAndRefreshIt.execute(httpException = exc) {
                    getUserDetail(userId = userId)
                }
            } else {
                hasUpdatedTheToken = false
                throw exc
            }
        }

    }

}