package com.chemasmas.fakestoreapi.core.network.models.responses

import com.google.gson.annotations.SerializedName

data class ErrorInvalidTokenMessage(
    @SerializedName("token_class") val tokenClass: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("message") val message: String,
)