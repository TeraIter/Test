package com.example.test.net.data

data class CartsOfUser(
    val carts: List<Carts>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
