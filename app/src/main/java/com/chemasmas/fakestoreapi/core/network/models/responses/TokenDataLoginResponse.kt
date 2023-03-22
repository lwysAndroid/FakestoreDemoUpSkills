package com.chemasmas.fakestoreapi.core.network.models.responses

import com.google.gson.annotations.SerializedName

data class TokenDataLoginResponse(
    @SerializedName("refresh") val refresh: String,
    @SerializedName("access") val access: String,
)
