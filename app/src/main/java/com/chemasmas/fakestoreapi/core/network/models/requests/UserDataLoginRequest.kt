package com.chemasmas.fakestoreapi.core.network.models.requests

import com.google.gson.annotations.SerializedName

data class UserDataLoginRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
)
