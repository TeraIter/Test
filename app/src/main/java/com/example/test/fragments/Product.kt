package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.models.SlideModel
import com.example.test.databinding.ProductFragmentBinding
import com.example.test.net.DummyJSON
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Product(
    private val itemId: Int
) : Fragment() {
    private lateinit var binding: ProductFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        CoroutineScope(Dispatchers.IO).launch {
            val product = DummyJSON.productApi.getById(itemId)
            val imageList = ArrayList<SlideModel>()
            product.images.forEach { imageList.add(SlideModel(it)) }
            activity?.runOnUiThread {
                binding.progressBar2.progress = 50
                binding.sliderImages.setImageList(imageList)

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