package com.chemasmas.fakestoreapi.core.network.models.responses

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    @SerializedName("access") val access: String,
)