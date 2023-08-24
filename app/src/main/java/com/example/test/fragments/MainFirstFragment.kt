package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.rv_adapters.ProductAdapter
import com.example.test.databinding.FragmentMainFirstBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.example.test.net.method.getProducts
import com.example.test.rv_adapters.scroll_states.FirstFragScrollState


class MainFirstFragment : Fragment() {
    private lateinit var binding: FragmentMainFirstBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var scrollState: FirstFragScrollState

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
                adapter = ProductAdapter(getProducts(0, requireActivity()), requireActivity())
                adapter.compactProducts.products.add(null)
                adapter.stateRestorationPolicy = StateRestorationPolicy.ALLOW
            }
            activity?.runOnUiThread {
                if (adapter.compactProducts.products.size > 1) {
                    if (!this@MainFirstFragment::scrollState.isInitialized) {
                        scrollState = FirstFragScrollState(10, requireActivity(), adapter)
                    }
                    binding.firstFragmentProductRV.addOnScrollListener(scrollState)
                    binding.firstFragmentProductRV.adapter = adapter
                    binding.centerPB.visibility = View.GONE
                    binding.firstFragmentProductRV.layoutManager = LinearLayoutManager(context)
                    binding.firstFragmentProductRV.visibility = View.VISIBLE
                    binding.firstFragmentProductRV.layoutManager = LinearLayoutManager(activity)
                } else {
                    binding.centerPB.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                }
            }
        }
    }
}