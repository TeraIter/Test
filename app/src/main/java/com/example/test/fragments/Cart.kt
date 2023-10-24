package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.CartFragmentBinding
import com.example.test.net.DummyJSON.userCartApi
import com.example.test.room.DB
import com.example.test.rv_adapters.CartAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Cart : Fragment() {
    private lateinit var binding: CartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CartFragmentBinding.inflate(inflater)
        return binding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        CoroutineScope(Dispatchers.IO).launch {
            val user = DB.getDB(requireActivity().applicationContext).userDao.getUser().getOrNull(0)


            activity?.runOnUiThread {

                if (user == null) {
                    binding.notLoggedTv.visibility = View.VISIBLE
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val adapter = CartAdapter(userCartApi.getUserCartById(user.idServer).carts.getOrNull(0), activity)
                        activity?.runOnUiThread {
                            if (adapter.itemCount > 0) {
                                binding.userCartRv.adapter = adapter
                                binding.userCartRv.layoutManager = LinearLayoutManager(context)
                                binding.userCartRv.visibility = View.VISIBLE
                            } else {
                                binding.noItemsTv.visibility = View.VISIBLE
                            }
                        }
                    }
                }

                binding.progressBar3.visibility = View.GONE
            }
        }
    }*/
}