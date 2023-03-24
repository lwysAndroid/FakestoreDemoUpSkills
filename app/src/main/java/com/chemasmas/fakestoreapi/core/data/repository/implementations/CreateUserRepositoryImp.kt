package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.CreateUserRepository
import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.requests.CreateUserRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.CreateUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateUserRepositoryImp @Inject constructor(
    private val upskillsNetworkDataSource: UpskillsNetworkDataSource,
) : CreateUserRepository {

    override fun createUser(createUserRequest: CreateUserRequest): Flow<CreateUserResponse> = flow {
        val response = upskillsNetworkDataSource.createUser(createUserRequest = createUserRequest)
        emit(response)
    }

}