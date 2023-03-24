package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import com.chemasmas.fakestoreapi.core.data.repository.UserListRepository
import com.chemasmas.fakestoreapi.core.data.repository.utils.ValidateInvalidTokenAndRefreshIt
import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.responses.UserListResponse
import retrofit2.HttpException
import javax.inject.Inject

class UserListRepositoryImp @Inject constructor(
    private val upskillsNetworkDataSource: UpskillsNetworkDataSource,
    private val tokensRepository: TokensRepository,
    private val validateInvalidTokenAndRefreshIt: ValidateInvalidTokenAndRefreshIt,
) : UserListRepository {

    private var hasUpdatedTheToken = false
    override suspend fun getUserList(): UserListResponse {
        try {
            return upskillsNetworkDataSource.getUserList(
                accessToken = tokensRepository.getBearerAccessToken()
            )
        } catch (exc: HttpException) {
            if (!hasUpdatedTheToken) {
                hasUpdatedTheToken = true
                return validateInvalidTokenAndRefreshIt.execute(httpException = exc) {
                    getUserList()
                }
            } else {
                hasUpdatedTheToken = false
                throw exc
            }

        }
    }

}