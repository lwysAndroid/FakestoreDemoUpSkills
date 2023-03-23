package com.chemasmas.fakestoreapi.core.network

import com.chemasmas.fakestoreapi.core.network.models.requests.UserDataLoginRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.TokenDataLoginResponse
import com.chemasmas.fakestoreapi.core.network.models.responses.UserListResponse

interface UpskillsNetworkDataSource {
    suspend fun performLogin(userDataLoginRequest: UserDataLoginRequest): TokenDataLoginResponse
    suspend fun getUserList(accessToken: String): UserListResponse
}