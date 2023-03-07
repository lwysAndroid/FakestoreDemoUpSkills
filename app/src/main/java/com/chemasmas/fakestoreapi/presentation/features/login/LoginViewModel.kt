package com.chemasmas.fakestoreapi.presentation.features.login

import androidx.lifecycle.ViewModel
import com.chemasmas.fakestoreapi.core.domain.ValidateEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase
) : ViewModel() {

}