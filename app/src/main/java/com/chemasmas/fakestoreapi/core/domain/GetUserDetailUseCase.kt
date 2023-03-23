package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.UserDetailRepository
import com.chemasmas.fakestoreapi.core.network.models.responses.UserDetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userDetailRepository: UserDetailRepository,
) {
    fun execute(userId: Int): Flow<UserDetailResponse> {
        return userDetailRepository.getUserDetail(userId = userId)
    }
}