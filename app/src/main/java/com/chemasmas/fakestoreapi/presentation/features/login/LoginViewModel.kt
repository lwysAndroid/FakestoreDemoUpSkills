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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dispatchersSource: DispatchersSource,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val makeLoginUseCase: MakeLoginUseCase,
) : ViewModel() {

    private val _invalidEmail = MutableStateFlow(0)
    val invalidEmail get() = _invalidEmail.asStateFlow()

    private val _invalidPassword = MutableStateFlow(0)
    val invalidPassword get() = _invalidPassword.asStateFlow()

    private val _succeedLogin = MutableStateFlow<Boolean>(false)
    val succeedLogin get() = _succeedLogin.asStateFlow()

    fun makeLogin(email: String, password: String) {
        viewModelScope.launch(dispatchersSource.io) {
            try {
                var isValidEmail = false
                validateEmailUseCase(email).collect() {
                    isValidEmail = it
                }
                if (isValidEmail.not()) {
                    _invalidEmail.value = R.string.invalid_email
                    return@launch
                }

                var isValidPassword = false
                validatePasswordUseCase(password).collectLatest {
                    isValidPassword = it
                }
                if (isValidPassword.not()) {
                    _invalidPassword.value = R.string.invalid_password
                    return@launch
                }

                makeLoginUseCase(email = email, password = password).collectLatest {
                    _succeedLogin.value = it
                }

            } catch (exc: Exception) {
                //TODO handled exceptions
            }

        }
    }

}