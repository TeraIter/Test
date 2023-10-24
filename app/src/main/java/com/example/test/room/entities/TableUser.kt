package com.example.test.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.net.data.user.User


@Entity
data class TableUser(
    var name: String,
    var surname: String,
    var email: String,
    var password: String,
    @PrimaryKey
    var id: Int = 1,
) {
    constructor(user: User) : this(
        user.name,
        user.surname,
        user.email,
        user.password
    )
}
