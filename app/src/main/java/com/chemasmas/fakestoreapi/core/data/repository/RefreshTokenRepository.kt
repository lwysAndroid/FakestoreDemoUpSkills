package com.chemasmas.fakestoreapi.core.data.repository

import com.chemasmas.fakestoreapi.core.network.models.responses.RefreshTokenResponse
import kotlinx.coroutines.flow.Flow

interface RefreshTokenRepository {
    suspend fun refreshToken(): Flow<RefreshTokenResponse>
}