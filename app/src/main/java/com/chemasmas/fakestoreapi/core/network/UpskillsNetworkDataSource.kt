package com.chemasmas.fakestoreapi.core.network

import com.chemasmas.fakestoreapi.core.network.models.requests.UserDataLoginRequest
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDetailRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.TokenDataLoginResponse
import com.chemasmas.fakestoreapi.core.network.models.responses.UserDetailResponse
import com.chemasmas.fakestoreapi.core.network.models.responses.UserListResponse

interface UpskillsNetworkDataSource {
    suspend fun performLogin(userDataLoginRequest: UserDataLoginRequest): TokenDataLoginResponse
    suspend fun getUserList(accessToken: String): UserListResponse

    suspend fun getUserDetail(
        accessToken: String,
        userDetailRequest: UserDetailRequest
    ): UserDetailResponse
}