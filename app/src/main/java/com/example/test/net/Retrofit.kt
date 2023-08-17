package com.example.test.net

import com.example.test.net.api.AccountAPI
import com.example.test.net.api.ProductAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object Retrofit {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.8.100:8080")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val accountApi = retrofit.create(AccountAPI::class.java)
}

object DummyJSON {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val productApi = retrofit.create(ProductAPI::class.java)
}