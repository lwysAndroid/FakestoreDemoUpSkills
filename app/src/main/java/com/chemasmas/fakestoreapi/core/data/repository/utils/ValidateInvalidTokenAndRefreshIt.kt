package com.chemasmas.fakestoreapi.core.data.repository.utils

import com.chemasmas.fakestoreapi.core.data.repository.RefreshTokenRepository
import com.chemasmas.fakestoreapi.core.network.models.INVALID_TOKEN_ERROR_CODE
import com.chemasmas.fakestoreapi.core.network.models.responses.ErrorInvalidToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import retrofit2.HttpException
import javax.inject.Inject

class ValidateInvalidTokenAndRefreshIt @Inject constructor(
    private val refreshTokenRepository: RefreshTokenRepository,
) {

    suspend fun <R> execute(
        httpException: HttpException,
        retryFunction: suspend () -> R
    ): R {
        httpException.response()?.errorBody()?.let { errorBody ->
            val gson = Gson()
            val errorInvalidToken =
                gson.fromJson(errorBody.string(), ErrorInvalidToken::class.java)
            if (errorInvalidToken.code == INVALID_TOKEN_ERROR_CODE) {
                try {
                    refreshTokenRepository.refreshToken().collect()
                } catch (except: Exception) {
                    throw httpException
                }
                return retryFunction.invoke()
            } else {
                throw httpException
            }
        } ?: run { throw httpException }
    }
}
