package com.chemasmas.fakestoreapi.core.network

import com.chemasmas.fakestoreapi.core.network.models.requests.CreateUserRequest
import com.chemasmas.fakestoreapi.core.network.models.requests.RefreshTokenRequest
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDataLoginRequest
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDetailRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.*

interface UpskillsNetworkDataSource {
    suspend fun performLogin(userDataLoginRequest: UserDataLoginRequest): TokenDataLoginResponse
    suspend fun getUserList(accessToken: String): UserListResponse

    suspend fun getUserDetail(
        accessToken: String,
        userDetailRequest: UserDetailRequest
    ): UserDetailResponse

    suspend fun createUser(
        createUserRequest: CreateUserRequest
    ): CreateUserResponse

    suspend fun refreshToken(
        accessToken: String,
        refreshTokenRequest: RefreshTokenRequest
    ): RefreshTokenResponse

}