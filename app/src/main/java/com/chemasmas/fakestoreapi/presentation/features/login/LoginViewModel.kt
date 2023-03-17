package com.chemasmas.fakestoreapi.presentation.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.domain.MakeLoginUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidateEmailUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dispatchersSource: DispatchersSource,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val makeLoginUseCase: MakeLoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun makeLogin(email: String, password: String) {
        _state.value = LoginState(isLoading = true)
        viewModelScope.launch(dispatchersSource.io) {
            try {
                var isValidEmail = false
                validateEmailUseCase.execute(email).collect() {
                    isValidEmail = it
                }
                if (isValidEmail.not()) {
                    _state.value = LoginState(
                        isLoading = false,
                        errorUser = R.string.invalid_email
                    )
                    return@launch
                }

                var isValidPassword = false
                validatePasswordUseCase.execute(password).collectLatest {
                    isValidPassword = it
                }
                if (isValidPassword.not()) {
                    _state.value =
                        LoginState(isLoading = false, errorPassword = R.string.invalid_password)
                    return@launch
                }

                makeLoginUseCase.execute(email = email, password = password).collectLatest {
                    //TODO go to next screen
                    _state.value = LoginState(
                        isLoading = false,
                        successLogin = true,
                        errorService = "Success Login"
                    )
                }

            } catch (exc: Exception) {
                _state.value =
                    LoginState(isLoading = false, successLogin = false, errorService = exc.message)
            }

        }
    }

    fun userIsTyping() {
        _state.update {
            it.copy(errorUser = null, errorPassword = null)
        }
    }

    data class LoginState(
        val isLoading: Boolean = false,
        val errorUser: Int? = null,
        val errorPassword: Int? = null,
        val errorService: String? = null,
        val successLogin: Boolean? = null
    )

}