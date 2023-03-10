package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.MakeLoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MakeLoginRepositoryImp @Inject constructor() : MakeLoginRepository {

    override fun makeLogin(email: String, password: String): Flow<Boolean> = flow {
        delay(2_000)
        emit(true)
    }

}