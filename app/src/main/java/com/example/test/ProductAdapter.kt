package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            productDesc.text = products.products[position].description
            productPrice.text = products.products[position].price.toString()
            productId.text = products.products[position].id.toString()
        }
    }

    override fun getItemCount(): Int {
        return products.products.size
    }
}