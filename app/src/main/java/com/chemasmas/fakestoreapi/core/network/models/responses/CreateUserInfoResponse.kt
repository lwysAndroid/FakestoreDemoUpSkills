package com.chemasmas.fakestoreapi.core.network.models.responses

import com.google.gson.annotations.SerializedName

data class CreateUserInfoResponse(
    @SerializedName("user_id") val id: Int?,
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("profile_icon") val profileIcon: String?,
)