package com.example.test.net.api

import com.example.test.net.data.CompactProducts
import com.example.test.net.data.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products/{id}")
    suspend fun getById(@Path("id") id: Int): Product

    @GET("products")
    suspend fun getAllWithParam(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 10,
        @Query("select") select: List<String> = listOf("title", "price", "thumbnail", "category", "rating")
    ): Response<CompactProducts>
}