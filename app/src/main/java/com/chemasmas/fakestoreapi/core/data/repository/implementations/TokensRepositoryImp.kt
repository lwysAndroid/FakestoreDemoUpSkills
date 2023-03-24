package com.chemasmas.fakestoreapi.core.data.repository.implementations

import android.content.Context
import android.content.SharedPreferences
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.data.repository.TokensRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokensRepositoryImp @Inject constructor(
    @ApplicationContext private val context: Context
) : TokensRepository {

    private val sharePreferences: SharedPreferences by lazy { getSharePReferences() }
    private fun getSharePReferences(): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.preference_tokens_key), Context.MODE_PRIVATE
        )
    }

    override suspend fun setRefreshToken(refresh: String) {
        with(sharePreferences.edit()) {
            putString(context.getString(R.string.refresh_token), refresh)
            apply()
        }
    }

    override suspend fun getRefreshToken(): String {
        return with(sharePreferences) {
            getString(context.getString(R.string.refresh_token), "") ?: ""
        }
    }

    override suspend fun setAccessToken(access: String) {
        with(sharePreferences.edit()) {
            putString(context.getString(R.string.access_token), access)
            apply()
        }
    }

    override suspend fun getAccessToken(): String {
        return with(sharePreferences) {
            getString(context.getString(R.string.access_token), "") ?: ""
        }
    }

    override suspend fun getBearerAccessToken(): String {
        return "Bearer ${getAccessToken()}"
    }

}