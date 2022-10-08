package ru.rt.finance.features.user.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/** * Пользователь - по умолчанию у нас есть он один **/

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String? = null,
)