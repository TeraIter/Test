package com.example.test.rv_adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.BottomProgressBarBinding
import com.example.test.databinding.RvProductBinding
import com.example.test.fragments.Product
import com.example.test.net.data.CompactProducts

class ProductAdapter(
    val compactProducts: CompactProducts,
    private val activity: FragmentActivity?
) : RecyclerView.Adapter<ProductAdapter.CustomViewHolder>() {
    private val itemType = 1
    private val progressBarType = 2

    open inner class CustomViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root)

    inner class DataViewHolder(val binding: RvProductBinding) : CustomViewHolder(binding)
    inner class ProgressViewHolder(binding: BottomProgressBarBinding) : CustomViewHolder(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == itemType) {
            val binding = RvProductBinding.inflate(layoutInflater, parent,false)
            DataViewHolder(binding)
        } else {
            val binding = BottomProgressBarBinding.inflate(layoutInflater, parent, false)
            ProgressViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        when(holder) {
            is DataViewHolder -> {
                holder.binding.apply {
                    CVproduct.setOnClickListener{changeFragment(compactProducts.products[position]?.id!!, activity)}
                    productItemTitle.text = compactProducts.products[position]?.title
                    productItemPrice.text = compactProducts.products[position]?.price.toString()
                    productItemId.text = compactProducts.products[position]?.id.toString()
                    productItemCategory.text = compactProducts.products[position]?.category
                    Glide.with(productItemThumbnail).load(compactProducts.products[position]?.thumbnail).into(productItemThumbnail)
                    productRating.rating = compactProducts.products[position]?.rating ?: 0.0F
                }
            }
            is ProgressViewHolder -> {}
        }

    }

    override fun getItemCount(): Int {
        return compactProducts.products.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (compactProducts.products[position] != null) {
            itemType
        } else {
            progressBarType
        }
    }

    private fun changeFragment(id: Int, activity: FragmentActivity?) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.mainFrameLayout, Product(id))
            addToBackStack(null)
            commit()
        }
    }
}