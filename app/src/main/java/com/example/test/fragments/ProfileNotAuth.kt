package com.example.test.fragments

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.test.R
import com.example.test.databinding.ProfileNotAuthFragmentBinding
import com.example.test.net.Spring.userApi
import com.example.test.net.data.user.UserAuth
import com.example.test.room.DB
import com.example.test.room.entities.TableUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileNotAuth : Fragment() {
    private lateinit var binding: ProfileNotAuthFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileNotAuthFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DB.getDB(requireActivity().applicationContext)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val user = userApi.authUser(UserAuth(email, password))
                if (user.isSuccessful) {
                    val tableUser = TableUser(user.body()!!)
                    db.userDao.upsertUser(tableUser)

                    activity?.runOnUiThread {
                        activity?.supportFragmentManager?.beginTransaction()?.apply {
                            replace(R.id.mainFrameLayout, ProfileAuth())
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

        binding.showPasswordCb.setOnClickListener {

            binding.passwordEt.let { et ->

                val start = et.selectionStart
                val end = et.selectionEnd
                if (binding.showPasswordCb.isChecked) {
                    et.transformationMethod = null
                } else {
                    et.transformationMethod = PasswordTransformationMethod()
                }
                et.setSelection(start, end)
            }
        }

        binding.noAccountTv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.mainFrameLayout, Registry())
                commit()
            }
        }
    }
}