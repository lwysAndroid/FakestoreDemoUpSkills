package com.chemasmas.fakestoreapi.core.network.retrofit

import com.chemasmas.fakestoreapi.core.network.UpskillsNetworkDataSource
import com.chemasmas.fakestoreapi.core.network.models.requests.CreateUserRequest
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDataLoginRequest
import com.chemasmas.fakestoreapi.core.network.models.requests.UserDetailRequest
import com.chemasmas.fakestoreapi.core.network.models.responses.CreateUserResponse
import com.chemasmas.fakestoreapi.core.network.models.responses.TokenDataLoginResponse
import com.chemasmas.fakestoreapi.core.network.models.responses.UserDetailResponse
import com.chemasmas.fakestoreapi.core.network.models.responses.UserListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitUpskillsApi {

    @POST(value = "login/")
    suspend fun performLogin(@Body userDataLoginRequest: UserDataLoginRequest): TokenDataLoginResponse

    @GET(value = "account/list_users/")
    suspend fun getUserList(@Header("AUTHORIZATION") accessToken: String): UserListResponse

    @POST(value = "account/details_users/")
    suspend fun getUseDetail(
        @Header("AUTHORIZATION") accessToken: String,
        @Body userDetailRequest: UserDetailRequest
    ): UserDetailResponse

    @POST(value = "/account/create_user/")
    suspend fun createUser(
        @Body createUserRequest: CreateUserRequest
    ): CreateUserResponse
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

    override suspend fun getUserList(accessToken: String): UserListResponse {
        return networkApi.getUserList(accessToken = accessToken)
    }

    override suspend fun getUserDetail(
        accessToken: String,
        userDetailRequest: UserDetailRequest
    ): UserDetailResponse {
        return networkApi.getUseDetail(
            accessToken = accessToken,
            userDetailRequest = userDetailRequest
        )
    }

    override suspend fun createUser(createUserRequest: CreateUserRequest): CreateUserResponse {
        return networkApi.createUser(createUserRequest = createUserRequest)
    }

}