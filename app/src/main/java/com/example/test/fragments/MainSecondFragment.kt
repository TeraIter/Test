package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test.databinding.FragmentMainSecondBinding
import com.example.test.room.DB
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

        CoroutineScope(Dispatchers.IO).launch {
            val user = DB.getDB(requireActivity().applicationContext).userDao.getUser().getOrNull(0)


            activity?.runOnUiThread {

                if (user == null) {
                    binding.notLoggedTv.visibility = View.VISIBLE
                } else {
                    binding.loggedOnTv.visibility = View.VISIBLE
                }

                binding.progressBar3.visibility = View.GONE
            }
        }
    }
}