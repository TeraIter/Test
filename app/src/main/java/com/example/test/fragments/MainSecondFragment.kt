package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test.databinding.FragmentMainSecondBinding
import com.example.test.net.Retrofit.accountApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainSecondFragment : Fragment() {
    private lateinit var binding: FragmentMainSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainSecondBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            val id = binding.editTextText.text.toString().toInt()

            CoroutineScope(Dispatchers.IO).launch {
                val account = accountApi.getAccountById(id)
                activity?.runOnUiThread {
                    binding.textView2.text = account?.name ?: "Error"
                }
            }
        }
    }
}