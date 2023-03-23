package com.chemasmas.fakestoreapi.presentation.features.profile_detail

import androidx.lifecycle.ViewModel
import com.chemasmas.fakestoreapi.core.config.DispatchersSource
import com.chemasmas.fakestoreapi.core.data.repository.models.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val dispatchersSource: DispatchersSource,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileDetailState())
    val state = _state.asStateFlow()

    fun getUserDetail(){

    }

    data class ProfileDetailState(
        val isLoading: Boolean = false,
        val errorService: String? = null,
        val userDetail: UserDetail? = null
    )
}