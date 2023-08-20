package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.rv_adapters.ProductAdapter
import com.example.test.databinding.FragmentMainFirstBinding
import com.example.test.net.DummyJSON.productApi
import com.example.test.net.data.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainFirstFragment : Fragment() {
    private lateinit var binding: FragmentMainFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lateinit var products: Products
        var skip = 0
        lateinit var adapter: ProductAdapter

        CoroutineScope(Dispatchers.IO).launch {
            products = getProducts(skip = skip)
            activity?.runOnUiThread {
                adapter = ProductAdapter(products, activity)
                binding.centerPB.visibility = View.GONE
                binding.firstFragmentProductRV.adapter = adapter
                binding.firstFragmentProductRV.layoutManager = LinearLayoutManager(context)
                binding.firstFragmentProductRV.visibility = View.VISIBLE
                skip += 10
            }
        }

        class ScrollState: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    CoroutineScope(Dispatchers.IO).launch {
                        products.products.addAll(getProducts(skip = skip).products)
                        skip += 10
                        activity?.runOnUiThread {
                            adapter.notifyItemRangeChanged(skip, products.products.size)
                        }
                    }
                }
            }
        }

        binding.firstFragmentProductRV.layoutManager = LinearLayoutManager(activity)
        binding.firstFragmentProductRV.addOnScrollListener(ScrollState())


    }

    private suspend fun getProducts(skip: Int): Products {
        return productApi.getAllWithParam(skip = skip)
    }

}