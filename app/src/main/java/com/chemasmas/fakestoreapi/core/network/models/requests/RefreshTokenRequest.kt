package com.chemasmas.fakestoreapi.core.network.models.requests

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(
    @SerializedName("refresh") val refresh: String,
)