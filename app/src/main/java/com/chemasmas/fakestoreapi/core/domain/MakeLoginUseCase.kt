package com.chemasmas.fakestoreapi.core.domain

import com.chemasmas.fakestoreapi.core.data.repository.MakeLoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MakeLoginUseCase @Inject constructor(
    private val makeLoginRepository: MakeLoginRepository,
) {

    operator fun invoke(email: String, password: String): Flow<Boolean> {
        return makeLoginRepository.makeLogin(email = email, password = password)
    }

}