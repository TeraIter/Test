package com.example.test.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.FragmentProfileBinding
import com.example.test.room.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DB.getDB(requireActivity().applicationContext)

        CoroutineScope(Dispatchers.IO).launch {
            val user = db.dao.getUser()[0]

            activity?.runOnUiThread {
                Glide.with(binding.imageIvProfile).load(user.image).into(binding.imageIvProfile)
                binding.usernameTvProfile.text = user.username
                binding.deleteUserBtn.setOnClickListener {

                    CoroutineScope(Dispatchers.IO).launch {

                        db.dao.deleteUser(user)

                        activity?.runOnUiThread {
                            activity?.supportFragmentManager?.beginTransaction()?.apply {
                                replace(R.id.mainFrameLayout, MainThirdFragment())
                                commit()
                            }
                        }
                    }
                }
            }
        }
    }
}