package com.example.test.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.FragmentProductBinding
import com.example.test.net.DummyJSON
import com.example.test.net.data.Product
import com.example.test.rv_adapters.ProductAdapter
import com.example.test.rv_adapters.ProductImagesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ItemFragment(
    private val itemId: Int
) : Fragment() {
    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var adapter: ProductImagesAdapter
        binding.rvImages.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        CoroutineScope(Dispatchers.IO).launch {
            val product = DummyJSON.productApi.getById(itemId)
            activity?.runOnUiThread {
                binding.progressBar2.progress = 50
                adapter = ProductImagesAdapter(product.images)
                binding.rvImages.adapter = adapter

                binding.apply {
                    productTitle.text = product.title
                    progressBar2.progress = 60
                    productDesciption.text = product.description
                    progressBar2.progress = 70
                    productBrand.text = product.brand
                    progressBar2.progress = 80
                    productCategory.text = product.category
                    progressBar2.progress = 90
                    productPrice.text = product.price.toString()
                    progressBar2.progress = 100
                    productLayout.visibility = View.VISIBLE
                    progressBar2.visibility = View.GONE
                }
            }
        }
    }
}