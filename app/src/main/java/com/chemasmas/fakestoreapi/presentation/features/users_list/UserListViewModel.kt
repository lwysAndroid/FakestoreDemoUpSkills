package com.chemasmas.fakestoreapi.presentation.features.users_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleUser
import com.chemasmas.fakestoreapi.core.domain.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val dispatchersSource: DispatchersSource,
    private val getUserListUseCase: GetUserListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UsersListState())
    val state = _state.asStateFlow()

    init {
        getUserList()
    }

    fun getUserList() {
        _state.update { userListState ->
            userListState.copy(
                errorService = null,
                isLoading = true
            )
        }
        viewModelScope.launch(dispatchersSource.io) {
            try {
                getUserListUseCase.execute().collectLatest { userListResponse ->
                    userListResponse.data.map { userFromService ->
                        SimpleUser(
                            id = userFromService.id,
                            username = userFromService.username,
                            email = userFromService.email,
                            firstName = userFromService.firstName,
                            lastName = userFromService.lastName,
                            profileIcon = userFromService.profileIcon,
                        )
                    }.also {
                        _state.update { userListState ->
                            userListState.copy(
                                userList = it,
                                isLoading = false
                            )
                        }
                    }
                }
            } catch (exc: Exception) {
                _state.value = UsersListState(errorService = exc.message)
            }
        }
    }

    fun wantToLogout() {
        _state.update {
            it.copy(
                wantToLogout = true
            )
        }
    }

    fun dismissLogoutDialog() {
        _state.update {
            it.copy(
                wantToLogout = false
            )
        }
    }

    data class UsersListState(
        val isLoading: Boolean = false,
        val wantToLogout: Boolean = false,
        val userList: List<SimpleUser> = emptyList(),
        val errorService: String? = null,
    )
}