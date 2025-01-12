package com.chemasmas.fakestoreapi.core.designSystem.models

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    object Default : ScreenState<Nothing>()
    data class Success<T>(val data: T) : ScreenState<T>()
    data class Error(val error: String) : ScreenState<Nothing>()
}