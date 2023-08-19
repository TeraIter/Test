package com.example.test

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.test.databinding.BottomProgressBarBinding
import com.example.test.databinding.ProductItemRvBinding
import com.example.test.net.data.Products

class ProductAdapter(
    val products: Products
) : RecyclerView.Adapter<ProductAdapter.CustomViewHolder>() {
    private val DATA = 1
    private val PROGRESS_BAR = 2

    inner open class CustomViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root)

    inner class DataViewHolder(val binding: ProductItemRvBinding) : CustomViewHolder(binding)
    inner class ProgressViewHolder(val binding: BottomProgressBarBinding) : CustomViewHolder(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == DATA) {
            val binding = ProductItemRvBinding.inflate(layoutInflater, parent,false)
            return DataViewHolder(binding)
        } else {
            val binding = BottomProgressBarBinding.inflate(layoutInflater, parent, false)
            return ProgressViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        when(holder) {
            is DataViewHolder -> {
                holder.binding.apply {
                    productTitle.text = products.products[position].title
                    productPrice.text = products.products[position].price.toString()
                    productId.text = products.products[position].id.toString()
                    productCategory.text = products.products[position].category
                    Glide.with(productImage).load(products.products[position].thumbnail).into(productImage)
                }
            }
            is ProgressViewHolder -> {

            }
        }

    }

    override fun getItemCount(): Int {
        return products.products.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (products.products.size > position) {
            return DATA
        } else {
            return PROGRESS_BAR
        }
    }
}