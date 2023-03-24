package com.chemasmas.fakestoreapi.core.data.repository

import kotlinx.coroutines.flow.Flow

interface ValidateSessionRepository {
    fun validateSession(): Flow<Boolean>
}