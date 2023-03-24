package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.CreateUserRepository
import com.chemasmas.fakestoreapi.core.data.repository.PerformLoginRepository
import com.chemasmas.fakestoreapi.core.network.models.requests.CreateUserRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.CreateUserResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val createUserRepository: CreateUserRepository,
) {
    fun execute(createUserRequest: CreateUserRequest): Flow<CreateUserResponse> {
        return createUserRepository.createUser(createUserRequest = createUserRequest)
    }
}