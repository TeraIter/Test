package com.example.test.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.ProfileAuthFragmentBinding
import com.example.test.room.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileAuth : Fragment() {
    private lateinit var binding: ProfileAuthFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileAuthFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DB.getDB(requireActivity().applicationContext)

        CoroutineScope(Dispatchers.IO).launch {
            val user = db.userDao.getUser()[0]

            activity?.runOnUiThread {
                binding.nameProfileTv.text = user.name
                binding.surnameProfileTv.text = user.surname


                binding.exitProfileBtn.setOnClickListener {

                    CoroutineScope(Dispatchers.IO).launch {

                        db.userDao.deleteUser(user)

                        activity?.runOnUiThread {
                            activity?.supportFragmentManager?.beginTransaction()?.apply {
                                replace(R.id.mainFrameLayout, ProfileNotAuth())
                                commit()
                            }
                        }
                    }
                }
            }
        }
    }
}