package com.chemasmas.fakestoreapi.core.data.repository

import com.chemasmas.fakestoreapi.core.network.models.requests.CreateUserRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.CreateUserResponse
import kotlinx.coroutines.flow.Flow

interface CreateUserRepository {
    fun createUser(createUserRequest: CreateUserRequest): Flow<CreateUserResponse>
}