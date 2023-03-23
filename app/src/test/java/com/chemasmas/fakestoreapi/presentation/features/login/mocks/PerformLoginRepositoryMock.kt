package com.chemasmas.fakestoreapi.presentation.features.login.mocks

import com.chemasmas.fakestoreapi.core.data.repository.PerformLoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PerformLoginRepositoryMock : PerformLoginRepository {

    override fun performLogin(email: String, password: String): Flow<Boolean> = flow {
        emit(true)
    }

}