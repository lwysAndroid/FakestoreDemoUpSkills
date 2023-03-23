package com.chemasmas.fakestoreapi.presentation.features.profile_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.data.repository.models.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val dispatchersSource: DispatchersSource,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileDetailState())
    val state = _state.asStateFlow()

    fun getUserDetail(userId: Int) {
        _state.update { profileDetailState ->
            profileDetailState.copy(
                errorService = null,
                isLoading = true
            )
        }
        viewModelScope.launch(dispatchersSource.io) {
            try {
            } catch (exc: Exception) {
                _state.update { profileDetailState ->
                    profileDetailState.copy(
                        errorService = exc.message,
                        isLoading = false
                    )
                }
            }
        }

    }

    data class ProfileDetailState(
        val isLoading: Boolean = false,
        val errorService: String? = null,
        val userDetail: UserDetail? = null
    )
}