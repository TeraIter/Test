package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.test.databinding.ActivityMainBinding
import com.example.test.fragments.Store
import com.example.test.fragments.Cart
import com.example.test.fragments.ProfileNotAuth
import com.example.test.fragments.ProfileAuth
import com.example.test.room.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment = Store()
        val secondFragment = Cart()
        val thirdFragment = ProfileNotAuth()

        changeScreen(firstFragment)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_store -> {
                    changeScreen(firstFragment)
                    true
                }
                R.id.page_cart -> {
                    changeScreen(secondFragment)
                    true
                }
                R.id.page_profile -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        val user = DB.getDB(applicationContext).userDao.getUser().getOrNull(0)
                        runOnUiThread {
                            if (user == null) {
                                changeScreen(thirdFragment)
                            } else {
                                changeScreen(ProfileAuth())
                            }
                        }
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun changeScreen(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFrameLayout, fragment)
            commit()
        }
    }
}