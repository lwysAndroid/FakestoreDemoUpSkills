package com.chemasmas.fakestoreapi.presentation.features.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.domain.CreateUserUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidateEmailUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidatePasswordUseCase
import com.chemasmas.fakestoreapi.core.domain.ValidateUserNameUseCase
import com.chemasmas.fakestoreapi.core.network.models.requests.CreateUserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dispatchersSource: DispatchersSource,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateUserNameUseCase: ValidateUserNameUseCase,
    private val createUserUseCase: CreateUserUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun performSignup(
        userName: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String,
        profileIcon: String,
    ) {
        _state.update {
            it.copy(isLoading = true, errorsFieldsSignUp = null, errorService = null)
        }

        viewModelScope.launch(dispatchersSource.io) {
            var isValidUserName = false
            validateUserNameUseCase.execute(userName).collect() {
                isValidUserName = it
            }

            var isValidPassword = false
            validatePasswordUseCase.execute(password).collectLatest {
                isValidPassword = it
            }

            var isValidEmail = false
            validateEmailUseCase.execute(email).collect() {
                isValidEmail = it
            }

            var isValidFirstName = false
            validateUserNameUseCase.execute(firstName).collect() {
                isValidFirstName = it
            }

            var isValidLastName = false
            validateUserNameUseCase.execute(lastName).collect() {
                isValidLastName = it
            }

            val hasErrors = !isValidUserName
                    || !isValidEmail
                    || !isValidPassword
                    || !isValidFirstName
                    || !isValidLastName
            if (hasErrors) {
                val errorsFieldsSignUp = ErrorsFieldsSignUp()
                errorsFieldsSignUp.errorUserName =
                    if (!isValidUserName) R.string.invalid_user_name else null
                errorsFieldsSignUp.errorEmail = if (!isValidEmail) R.string.invalid_email else null
                errorsFieldsSignUp.errorPassword =
                    if (!isValidPassword) R.string.invalid_password else null
                errorsFieldsSignUp.errorFirstName =
                    if (!isValidFirstName) R.string.invalid_first_name else null
                errorsFieldsSignUp.errorLastName =
                    if (!isValidLastName) R.string.invalid_last_name else null
                _state.update {
                    it.copy(isLoading = false, errorsFieldsSignUp = errorsFieldsSignUp)
                }

            } else {
                _state.update {
                    it.copy(errorsFieldsSignUp = null)
                }
                try {
                    val createUserRequest = CreateUserRequest(
                        username = userName,
                        password = password,
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        profileIcon = profileIcon,
                    )
                    createUserUseCase.execute(createUserRequest).collectLatest {
                        _state.update {
                            it.copy(isLoading = false, successSignUp = true)
                        }
                    }

                } catch (exc: Exception) {
                    _state.update {
                        it.copy(isLoading = false, errorService = exc.message)
                    }
                }

            }

        }

    }

    fun resetState() {
        _state.value = SignUpState()
    }

    data class SignUpState(
        val isLoading: Boolean = false,
        val successSignUp: Boolean? = null,
        val errorService: String? = null,
        val errorsFieldsSignUp: ErrorsFieldsSignUp? = null,
    )

    data class ErrorsFieldsSignUp(
        var errorUserName: Int? = null,
        var errorEmail: Int? = null,
        var errorPassword: Int? = null,
        var errorFirstName: Int? = null,
        var errorLastName: Int? = null,
    ) {

    }
}