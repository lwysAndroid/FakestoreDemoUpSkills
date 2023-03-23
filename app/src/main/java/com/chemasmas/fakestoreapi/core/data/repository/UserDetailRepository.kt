package com.chemasmas.fakestoreapi.core.data.repository

import com.chemasmas.fakestoreapi.core.network.models.responses.UserDetailResponse
import kotlinx.coroutines.flow.Flow

interface UserDetailRepository {
    fun getUserDetail(userId:Int): Flow<UserDetailResponse>
}