package com.example.test.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.room.dao.UserDao
import com.example.test.room.entities.TableUser


@Database(
    entities = [TableUser::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract val dao: UserDao
}