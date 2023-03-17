package com.chemasmas.fakestoreapi.core.network

import com.chemasmas.fakestoreapi.core.network.models.requests.UserDataLoginRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.TokenDataLoginResponse

interface UpskillsNetworkDataSource {
    suspend fun performLogin(userDataLoginRequest: UserDataLoginRequest): TokenDataLoginResponse
}