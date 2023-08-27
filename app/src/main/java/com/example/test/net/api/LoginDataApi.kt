package com.example.test.net.api

import com.example.test.net.data.DataUser
import com.example.test.net.data.LoginData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginDataApi {
    @POST("auth/login")
    suspend fun login(@Body loginData: LoginData): Response<DataUser>
}