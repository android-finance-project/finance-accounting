package ru.rt.finance.features.user.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/** * Пользователь - по умолчанию у нас есть он один **/

@Entity(tableName = TableUser.tableNameUser)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String? = null,
)

object TableUser {
    const val tableNameUser = "user"
}