package com.chemasmas.fakestoreapi.core.domain

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class ValidatePasswordUseCaseTest {
    private val validatePasswordUseCase = ValidatePasswordUseCase()

    @Test
    fun `Empty password`() = runTest {
        validatePasswordUseCase("").collectLatest {
            assertEquals(false, it)
        }
    }

    @Test
    fun `Correct password`() = runTest {
        validatePasswordUseCase("1234ABC").collectLatest {
            assertEquals(true, it)
        }
    }

}