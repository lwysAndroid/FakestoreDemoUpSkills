package com.chemasmas.fakestoreapi.core.data.repository

interface TokensRepository {
    suspend fun setRefreshToken(refresh: String)
    suspend fun getRefreshToken(): String
    suspend fun setAccessToken(access: String)
    suspend fun getAccessToken(): String
    suspend fun getBearerAccessToken(): String
}