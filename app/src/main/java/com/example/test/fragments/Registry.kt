package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.databinding.RegistryFragmentBinding
import com.example.test.net.Spring.userApi
import com.example.test.net.data.user.User
import com.example.test.room.DB
import com.example.test.room.entities.TableUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Registry : Fragment() {
    private lateinit var binding: RegistryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegistryFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DB.getDB(requireActivity().applicationContext)

        binding.registerBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val name = binding.nameRegistryEt.text.toString()
                val surname = binding.surnameRegistryEt.text.toString()
                val email = binding.emailRegistryEt.text.toString()
                val password = binding.passwordRegistryEt.text.toString()

                val responseUser = userApi.addUser(User(name, surname, email, password))

                if (responseUser.isSuccessful) {
                    db.userDao.upsertUser(TableUser(responseUser.body()!!))

                    activity?.runOnUiThread{
                        activity?.supportFragmentManager?.beginTransaction()?.apply {
                            replace(R.id.mainFrameLayout, ProfileAuth())
                            commit()
                        }
                    }
                }
            }
        }
    }
}