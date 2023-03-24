package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.UserListRepository
import com.chemasmas.fakestoreapi.core.network.models.responses.UserListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userListRepository: UserListRepository,
) {
    fun execute(): Flow<UserListResponse> = flow {
        emit(userListRepository.getUserList())
    }
}