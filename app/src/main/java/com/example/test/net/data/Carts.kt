package com.example.test.net.data

data class Carts (
    val id: Int,
    val products: List<CartProduct>,
    val total: Int,
    val discountedTotal: Int,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
)