package com.example.test.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.test.room.entities.TableUser

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: TableUser)

    @Delete
    suspend fun deleteUser(user: TableUser)

    @Query("SELECT * FROM tableuser")
    suspend fun getUser(): List<TableUser>
}