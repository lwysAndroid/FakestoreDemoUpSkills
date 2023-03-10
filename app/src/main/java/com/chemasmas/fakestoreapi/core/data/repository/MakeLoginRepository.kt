package com.chemasmas.fakestoreapi.core.data.repository

import kotlinx.coroutines.flow.Flow

interface MakeLoginRepository {
    fun makeLogin(email: String, password: String): Flow<Boolean>
}