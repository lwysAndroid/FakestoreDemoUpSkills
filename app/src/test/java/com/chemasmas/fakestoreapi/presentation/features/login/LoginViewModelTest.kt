package com.chemasmas.fakestoreapi.presentation.features.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.domain.MakeLoginUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidateEmailUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidatePasswordUseCase
import com.chemasmas.fakestoreapi.presentation.features.login.mocks.MakeLoginRepositoryMock
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

    private val makeLoginRepositoryMock = MakeLoginRepositoryMock()

    private val viewModel = LoginViewModel(
        dispatchersSource = dispatchersSource,
        validateEmailUseCase = ValidateEmailUseCase(),
        validatePasswordUseCase = ValidatePasswordUseCase(),
        makeLoginUseCase = MakeLoginUseCase(makeLoginRepositoryMock),
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `Login with empty email`() {
        viewModel.makeLogin(email = "", password = "")
        dispatcher.scheduler.advanceUntilIdle()
        val invalidEmail = viewModel.state.value.errorUser
        assertEquals(R.string.invalid_email, invalidEmail)
    }

    @Test
    fun `Login with invalid email`() {
        viewModel.makeLogin(email = "someEmail@gmail.", password = "")
        dispatcher.scheduler.advanceUntilIdle()
        val invalidEmail = viewModel.state.value.errorUser
        assertEquals(R.string.invalid_email, invalidEmail)
    }

    @Test
    fun `Login with empty password`() {
        viewModel.makeLogin(email = "someEmail@gmail.com", password = "")
        dispatcher.scheduler.advanceUntilIdle()
        val invalidPassword = viewModel.state.value.errorPassword
        assertEquals(R.string.invalid_password, invalidPassword)
    }

    @Test
    fun `Correct login`() {
        viewModel.makeLogin(email = "someEmail@gmail.com", password = "1234password")
        dispatcher.scheduler.advanceUntilIdle()
        val successLogin = viewModel.state.value.successLogin
        assertEquals(true, successLogin)
    }

    @Test
    fun `User is typing`() {
        viewModel.makeLogin(email = "", password = "")
        dispatcher.scheduler.advanceUntilIdle()
        var invalidEmail = viewModel.state.value.errorUser
        assertEquals(R.string.invalid_email, invalidEmail)
        viewModel.userIsTyping()
        dispatcher.scheduler.advanceUntilIdle()
        invalidEmail = viewModel.state.value.errorUser
        assertEquals(null, invalidEmail)
    }

}