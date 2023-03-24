package com.chemasmas.fakestoreapi.core.data.repository

import com.chemasmas.fakestoreapi.core.network.models.requests.UserDetailRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.UserDetailResponse
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    suspend fun getUserDetail(userId: Int): UserDetailResponse
}