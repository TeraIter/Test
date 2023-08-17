package com.example.test.net.api

import com.example.test.net.data.Account
import com.example.test.net.data.ListOfAccounts
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountAPI {
    @GET("account")
    suspend fun getAllAccount(): ListOfAccounts

    @POST("account/add")
    suspend fun addAccount(account: Account)

    @GET("account/{id}")
    suspend fun getAccountById(@Path("id") id: Int): Account?
}