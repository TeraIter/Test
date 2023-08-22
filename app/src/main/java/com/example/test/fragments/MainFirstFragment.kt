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
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy


class MainFirstFragment : Fragment() {
    private lateinit var binding: FragmentMainFirstBinding
    private lateinit var adapter: ProductAdapter
    var skip = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!this@MainFirstFragment::adapter.isInitialized) {
                adapter = ProductAdapter(getProducts(skip = skip), activity)
                skip += 10
            }
            activity?.runOnUiThread {
                binding.centerPB.visibility = View.GONE
                binding.firstFragmentProductRV.adapter = adapter
                adapter.stateRestorationPolicy = StateRestorationPolicy.ALLOW
                binding.firstFragmentProductRV.layoutManager = LinearLayoutManager(context)
                binding.firstFragmentProductRV.visibility = View.VISIBLE
            }
        }

        class ScrollState: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    CoroutineScope(Dispatchers.IO).launch {
                        adapter.products.products.addAll(getProducts(skip).products)
                        skip += 10
                        activity?.runOnUiThread {
                            adapter.notifyItemRangeChanged(skip, adapter.products.products.size)
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