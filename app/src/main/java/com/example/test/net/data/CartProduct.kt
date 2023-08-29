package com.example.test.net.data

data class CartProduct(
    val id: Int,
    val title: String,
    val price: Int,
    val quantity: Int,
    val total: Int,
    val discountPercentage: Float,
    val discountedPrice: Int
)
