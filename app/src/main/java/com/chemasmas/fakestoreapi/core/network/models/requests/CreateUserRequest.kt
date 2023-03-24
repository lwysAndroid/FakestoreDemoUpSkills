package com.chemasmas.fakestoreapi.core.network.models.requests

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("profile_icon") val profileIcon: String,
)