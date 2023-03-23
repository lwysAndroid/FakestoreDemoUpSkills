package com.chemasmas.fakestoreapi.core.network.models.responses

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("success") val success: Boolean?,
    @SerializedName("data") val data: UserDetailInfoResponse,
    @SerializedName("status") val status: Int?,
)
