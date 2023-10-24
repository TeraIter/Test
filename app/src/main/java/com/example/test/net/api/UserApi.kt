package com.example.test.net.api

import com.example.test.net.data.user.User
import com.example.test.net.data.user.UserAuth
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/user/add")
    suspend fun addUser(@Body user: User) : Response<User>

    @POST("/user/auth")
    suspend fun authUser(@Body auth: UserAuth) : Response<User>
}