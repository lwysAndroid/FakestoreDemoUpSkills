package com.chemasmas.fakestoreapi.core.data.repository.implementations

import com.chemasmas.fakestoreapi.core.data.repository.MakeLoginRepository
import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDataLoginRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MakeLoginRepositoryImp @Inject constructor(
    private val upskillsNetworkDataSource: UpskillsNetworkDataSource
) : MakeLoginRepository {

    override fun makeLogin(email: String, password: String): Flow<Boolean> = flow {
        val response = upskillsNetworkDataSource.performLogin(
            userDataLoginRequest = UserDataLoginRequest(
                username = email,
                password = password
            )
        )
        emit(true)
    }

}