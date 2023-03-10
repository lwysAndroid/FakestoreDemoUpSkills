package com.chemasmas.fakestoreapi.presentation.features.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.ApolloClient
import com.chemasmas.CountriesQuery
import com.chemasmas.fakestoreapi.R
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.designSystem.models.ScreenState
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

    private val _loginScreenState =
        MutableStateFlow<ScreenState<Any>>(ScreenState.Default)
    val loginScreenState get() = _loginScreenState.asStateFlow()


    fun makeLogin(email: String, password: String) {
        testGraphQLCall()
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
                _invalidEmail.value = 0

                var isValidPassword = false
                validatePasswordUseCase(password).collectLatest {
                    isValidPassword = it
                }
                if (isValidPassword.not()) {
                    _invalidPassword.value = R.string.invalid_password
                    return@launch
                }
                _invalidPassword.value = 0

                _loginScreenState.value = ScreenState.Loading

                makeLoginUseCase(email = email, password = password).collectLatest {
                    _loginScreenState.value =
                        ScreenState.Success(data = Any())
                }

            } catch (exc: Exception) {
                //TODO handled exceptions
            }

        }
    }

    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()

    fun testGraphQLCall() {
        /*TODO this call to the graphQL service should be move to the data layer in a remote data
        *  source an then all the missing layers required to retrieve the data should be built,
        *  like the repository, the use cases, the models for each layer, the mappers, etc. also
        *  all the dependencies should be supplied using hilt
        */
        viewModelScope.launch {
            val response = apolloClient.query(CountriesQuery()).execute()
            Log.d("LaunchList", "Success ${response.data}")
            //Country(name=Antigua and Barbuda, capital=Saint John's, code=AG, emoji=🇦🇬)
        }
    }

}