package com.example.test.net.data

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)


data class CompactProducts(
    var products: MutableList<Product?>,
    val total: Int,
    val skip: Int,
    val limit: Int
)