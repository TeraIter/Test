package com.example.test.rv_adapters.scroll_states

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.test.net.method.getProducts
import com.example.test.rv_adapters.ProductAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstFragScrollState(
    private var skip: Int,
    private val activity: FragmentActivity,
    private val adapter: ProductAdapter
) : RecyclerView.OnScrollListener() {
    private var isLoading = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {

            CoroutineScope(Dispatchers.IO).launch {
                if (!isLoading) {
                    isLoading = true
                    val compactProducts = getProducts(skip, activity)

                    if (compactProducts.products.isNotEmpty()) {
                        adapter.compactProducts.products.addAll(skip, compactProducts.products)
                        skip += 10
                    } else {
                        if (compactProducts.total == -1) {
                            activity.runOnUiThread {
                                Toast.makeText(activity, "Check your internet", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                            if (adapter.compactProducts.products.size > skip) adapter.compactProducts
                                .products.removeAt(skip)
                            activity.runOnUiThread {
                                Toast.makeText(activity, "That's All", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    activity.runOnUiThread {
                        adapter.notifyItemRangeChanged(skip, adapter.compactProducts.products.size)
                    }

                    isLoading = false
                }
            }
        }

    }
}
