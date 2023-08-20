package com.example.test.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.RvProductImageBinding

class ProductImagesAdapter(
    private val images: List<String>
) : RecyclerView.Adapter<ProductImagesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RvProductImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvProductImageBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(productImage).load(images[position]).into(productImage)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}