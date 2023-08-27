package com.example.test.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.net.data.DataUser


@Entity
data class TableUser(
    val idServer: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val token: String,
    @PrimaryKey
    val id: Int = 1
) {
    constructor(dataUser: DataUser) : this(
        dataUser.id,
        dataUser.username,
        dataUser.email,
        dataUser.firstName,
        dataUser.lastName,
        dataUser.gender,
        dataUser.image,
        dataUser.token
    )
}
