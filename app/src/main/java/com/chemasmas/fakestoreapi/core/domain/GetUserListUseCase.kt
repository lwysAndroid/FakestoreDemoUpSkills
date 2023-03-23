package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.UserListRepository
import com.chemasmas.fakestoreapi.core.network.models.responses.UserListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userListRepository: UserListRepository,
) {
    fun execute(): Flow<UserListResponse> {
        return userListRepository.getUserList()
    }
}