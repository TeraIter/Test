package com.example.test.net

import com.example.test.net.api.ProductApi
import com.example.test.net.api.UserApi
import com.example.test.net.api.UserCartApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object DummyJSON {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val productApi: ProductApi = retrofit.create(ProductApi::class.java)
    val userCartApi: UserCartApi = retrofit.create(UserCartApi::class.java)
}

object Spring {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.8.100:8080")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val userApi: UserApi = retrofit.create(UserApi::class.java)
}