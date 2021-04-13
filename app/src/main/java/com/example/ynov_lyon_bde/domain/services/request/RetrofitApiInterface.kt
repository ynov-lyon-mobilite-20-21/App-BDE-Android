package com.example.ynov_lyon_bde.domain.services.request

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface RetrofitApiInterface {

    @POST("api/users")
    suspend fun createUser(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("api/auth/login")
    suspend fun loginUser(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("api/users/me")
    suspend fun getUser(@Header("Authorization") token: String?): Response<ResponseBody>

    @POST("api/auth/refreshToken")
    suspend fun refreshToken(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("/api/ticket/{id}/check")
    suspend fun checkTicket(@Header("Authorization") token: String?, @Path("id") id: String?):Response<ResponseBody>
}
