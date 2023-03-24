package com.chemasmas.fakestoreapi.core.network.models.responses

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("success") val success: Boolean?,
    @SerializedName("data") val data: CreateUserInfoResponse,
    @SerializedName("status") val status: Int?,
)