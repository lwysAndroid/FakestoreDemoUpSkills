package com.chemasmas.fakestoreapi.core.network.retrofit

import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDataLoginRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.TokenDataLoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitUpskillsApi {

    @POST(value = "login/")
    suspend fun performLogin(@Body userDataLoginRequest: UserDataLoginRequest): TokenDataLoginResponse
}

private const val UPSKILLS_BASE_URL = "http://13.233.102.144:8000/"

@Singleton
class RetrofitUpskills @Inject constructor() : UpskillsNetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(UPSKILLS_BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    // TODO: Decide logging logic
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )

        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitUpskillsApi::class.java)

    override suspend fun performLogin(userDataLoginRequest: UserDataLoginRequest): TokenDataLoginResponse {
        return networkApi.performLogin(userDataLoginRequest = userDataLoginRequest)
    }


}