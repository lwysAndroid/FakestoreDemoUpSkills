package com.chemasmas.fakestoreapi.core.data.repository

import com.chemasmas.fakestoreapi.core.network.models.responses.UserListResponse
import kotlinx.coroutines.flow.Flow

interface UserListRepository {
    suspend fun getUserList(): UserListResponse
}