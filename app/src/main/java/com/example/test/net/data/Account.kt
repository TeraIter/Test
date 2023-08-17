package com.example.test.net.data

data class Account(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)

data class ListOfAccounts(
    val accounts: List<Account>
)