package com.example.test

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ProductItemRvBinding
import com.example.test.net.data.Products

class ProductAdapter(
    val products: Products
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding: ProductItemRvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =ProductItemRvBinding.inflate(layoutInflater,parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            productTitle.text = products.products[position].title
            productPrice.text = products.products[position].price.toString()
            productId.text = products.products[position].id.toString()
            productCategory.text = products.products[position].category
            Glide.with(productImage).load(products.products[position].thumbnail).into(productImage)
        }
    }

    override fun getItemCount(): Int {
        return products.products.size
    }
}