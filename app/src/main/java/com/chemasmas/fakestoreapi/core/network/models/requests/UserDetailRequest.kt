package com.chemasmas.fakestoreapi.core.network.models.requests

import com.google.gson.annotations.SerializedName

data class UserDetailRequest(
    @SerializedName("id") val id: Int,
)
