package com.chemasmas.fakestoreapi.core.data.repository

import kotlinx.coroutines.flow.Flow

interface PerformLoginRepository {
    fun performLogin(email: String, password: String): Flow<Boolean>
}