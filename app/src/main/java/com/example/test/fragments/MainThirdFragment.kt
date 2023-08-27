package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.databinding.FragmentMainThirdBinding
import com.example.test.net.DummyJSON.loginDataApi
import com.example.test.net.data.LoginData
import com.example.test.room.DB
import com.example.test.room.entities.TableUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainThirdFragment : Fragment() {
    private lateinit var binding: FragmentMainThirdBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainThirdBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DB.getDB(requireActivity().applicationContext)

        binding.btnLogin.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val password = binding.passwordEt.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val user = loginDataApi.login(LoginData(username, password))
                if (user.isSuccessful) {
                    val tableUser = TableUser(user.body()!!)
                    db.userDao.upsertUser(tableUser)

                    activity?.runOnUiThread {
                        activity?.supportFragmentManager?.beginTransaction()?.apply {
                            replace(R.id.mainFrameLayout, ProfileFragment())
                            commit()
                        }
                    }
                } else {
                    activity?.runOnUiThread {
                        Toast.makeText(context, "Wrong info", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}