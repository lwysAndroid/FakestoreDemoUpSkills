package com.chemasmas.fakestoreapi.core.data.repository

import kotlinx.coroutines.flow.Flow

interface CloseSessionRepository {
    fun closeSession(): Flow<Boolean>
}