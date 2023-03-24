package com.chemasmas.fakestoreapi.core.network.models.responses

import com.google.gson.annotations.SerializedName

data class ErrorInvalidToken(
    @SerializedName("detail") val detail: String,
    @SerializedName("code") val code: String,
    @SerializedName("messages") val messages: List<ErrorInvalidTokenMessage>
)