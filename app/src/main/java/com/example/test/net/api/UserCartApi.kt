package com.example.test.net.api

import com.example.test.net.data.CartsOfUser
import com.example.test.net.data.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface UserCartApi {
    @GET("carts/user/{id}")
    suspend fun getUserCartById(@Path("id") id: Int): CartsOfUser

    @GET("products/{id}?select=thumbnail")
    suspend fun getProductThumbnailById(@Path("id") id: Int): Product
}