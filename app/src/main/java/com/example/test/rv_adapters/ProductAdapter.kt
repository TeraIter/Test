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
import com.example.test.fragments.ItemFragment
import com.example.test.net.data.Products

class ProductAdapter(
    val products: Products,
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
                    CVproduct.setOnClickListener{changeFragment(products.products[position].id, activity)}
                    productItemTitle.text = products.products[position].title
                    productItemPrice.text = products.products[position].price.toString()
                    productItemId.text = products.products[position].id.toString()
                    productItemCategory.text = products.products[position].category
                    Glide.with(productItemThumbnail).load(products.products[position].thumbnail).into(productItemThumbnail)
                }
            }
            is ProgressViewHolder -> {}
        }

    }

    override fun getItemCount(): Int {
        return products.products.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (products.products.size > position) {
            itemType
        } else {
            progressBarType
        }
    }

    private fun changeFragment(id: Int, activity: FragmentActivity?) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.mainFrameLayout, ItemFragment(id))
            addToBackStack(null)
            commit()
        }
    }
}