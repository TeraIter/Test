package com.example.test.net.method

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.test.net.DummyJSON
import com.example.test.net.data.CompactProducts
import java.net.UnknownHostException


suspend fun getProducts(skip: Int, activity: FragmentActivity?): CompactProducts {
    return try {
        val response = DummyJSON.productApi.getAllWithParam(skip)

        if (response.isSuccessful) {
            response.body()!!
        } else {
            Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
            CompactProducts(mutableListOf(), 100, 10, 10)
        }
    } catch (e: UnknownHostException) {
        CompactProducts(mutableListOf(), -1, 10, 10)
    }
}