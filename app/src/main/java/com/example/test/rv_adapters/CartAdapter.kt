package com.example.test.rv_adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.CartItemBinding
import com.example.test.net.DummyJSON.userCartApi
import com.example.test.net.data.Carts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartAdapter(
    private val carts: Carts?,
    val activity: Activity?
): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        if (carts != null) {
            holder.binding.apply {
                titleCartProductTv.text = carts.products[position].title
                priceCartProductTv.text = carts.products[position].price.toString()
                quantityCartProductTv.text = carts.products[position].quantity.toString()
                totalCartProductTv.text = carts.products[position].total.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val product = userCartApi.getProductThumbnailById(carts.products[position].id)
                    activity?.runOnUiThread {
                        Glide.with(imageCartProductIv).load(product.thumbnail)
                            .into(imageCartProductIv)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return carts?.products?.size ?: 0
    }
}