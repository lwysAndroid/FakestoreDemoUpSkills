package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import javax.inject.Inject

class TokensRepositoryImp @Inject constructor() : TokensRepository {

    companion object {
        private var refresh = ""
        private var access = ""
    }

    override suspend fun setRefreshToken(refresh: String) {
        TokensRepositoryImp.refresh = refresh
    }

    override suspend fun getRefreshToken(): String {
        return refresh
    }

    override suspend fun setAccessToken(access: String) {
        TokensRepositoryImp.access = access
    }

    override suspend fun getAccessToken(): String {
        return access
    }

    override suspend fun getBearerAccessToken(): String {
        return "Bearer $access"
    }
}