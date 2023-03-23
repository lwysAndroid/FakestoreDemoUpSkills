package com.chemasmas.fakestoreapi.presentation.features.signUp

import androidx.lifecycle.ViewModel
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dispatchersSource: DispatchersSource,
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    data class SignUpState(
        val isLoading: Boolean = false,
        val errorService: String? = null,
        val errorUserName: Int? = null,
    )
}