package com.chemasmas.fakestoreapi.presentation.features.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.domain.MakeLoginUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidateEmailUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidatePasswordUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    private val dispatchersSource = object : DispatchersSource {
        override val main: CoroutineDispatcher
            get() = dispatcher
        override val io: CoroutineDispatcher
            get() = dispatcher
    }

    private val viewModel = LoginViewModel(
        dispatchersSource = dispatchersSource,
        validateEmailUseCase = ValidateEmailUseCase(),
        validatePasswordUseCase = ValidatePasswordUseCase(),
        makeLoginUseCase = MakeLoginUseCase(),
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `Login with empty email`() {
        viewModel.makeLogin(email = "", password = "")
        dispatcher.scheduler.advanceUntilIdle()
        val invalidEmail = viewModel.invalidEmail.value
        assertEquals("The email doesn't accomplish with the format", invalidEmail)
    }

    @Test
    fun `Login with invalid email`() {
//            assertEquals(false, it)
    }

    @Test
    fun `Login with empty password`() {
    }

    @Test
    fun `Correct login`() {
    }

}