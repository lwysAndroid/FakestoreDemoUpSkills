package com.chemasmas.fakestoreapi.presentation.features.profile_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.data.repository.models.UserDetail
import com.chemasmas.fakestoreapi.core.domain.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val dispatchersSource: DispatchersSource,
    private val getUserDetailUseCase: GetUserDetailUseCase,
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
                getUserDetailUseCase.execute(userId = userId).collectLatest { userDetailResponse ->
                    val userDetail = userDetailResponse.data.let {
                        UserDetail(
                            id = it.id,
                            username = it.username,
                            email = it.email,
                            firstName = it.firstName,
                            lastName = it.lastName,
                            profileIcon = it.profileIcon,
                        )
                    }

                    _state.update { profileDetailState ->
                        profileDetailState.copy(
                            userDetail = userDetail,
                            isLoading = false
                        )
                    }
                }
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