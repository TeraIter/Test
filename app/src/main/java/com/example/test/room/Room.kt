package com.example.test.room

import android.content.Context
import androidx.room.Room
import com.example.test.room.db.UserDatabase

object DB {
    fun getDB(context: Context) = Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        "User"
    ).build()
}