package com.chemasmas.fakestoreapi.core.network.models.responses

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("success") val success: Boolean?,
    @SerializedName("data") val data: ArrayList<UserInfoResponse>,
    @SerializedName("status") val status: Int?,
)
