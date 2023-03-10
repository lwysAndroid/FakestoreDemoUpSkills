package com.chemasmas.fakestoreapi.core.domain


import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*


class ValidateEmailUseCaseTest {
    private val validateEmailUseCase = ValidateEmailUseCase()

    @Test
    fun `Empty email`() = runTest {
        validateEmailUseCase.execute("").collectLatest {
            assertEquals(false, it)
        }
    }

    @Test
    fun `Incomplete email`() = runTest {
        validateEmailUseCase.execute("someEmail").collectLatest {
            assertEquals(false, it)
        }
    }

    @Test
    fun `Incomplete email without com`() = runTest {
        validateEmailUseCase.execute("someEmail@gmail.").collectLatest {
            assertEquals(false, it)
        }
    }

    @Test
    fun `Correct email`() = runTest {
        validateEmailUseCase.execute("someEmail@gmail.com").collectLatest {
            assertEquals(true, it)
        }
    }

}