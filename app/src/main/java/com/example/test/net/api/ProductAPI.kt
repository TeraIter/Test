package com.example.test.net.api

import com.example.test.net.data.Product
import com.example.test.net.data.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductAPI {
    @GET("products/{id}")
    suspend fun getById(@Path("id") id: Int): Product

    @GET("products")
    suspend fun getAllWithParam(@Query("limit") limit: Int, @Query("skip") skip: Int): Products
}