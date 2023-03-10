package com.chemasmas.fakestoreapi.presentation.features.login.mocks

import com.chemasmas.fakestoreapi.core.data.repository.MakeLoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MakeLoginRepositoryMock : MakeLoginRepository {

    override fun makeLogin(email: String, password: String): Flow<Boolean> = flow {
        emit(true)
    }

}