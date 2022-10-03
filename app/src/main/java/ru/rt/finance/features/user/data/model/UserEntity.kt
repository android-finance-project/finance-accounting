package ru.rt.finance.features.user.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** * Пользователь - по умолчанию у нас есть он один **/

@Entity(tableName = TableUser.tableNameUser)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user_entity")
    val idUserEntity: Int = 1,
    @ColumnInfo(name = "name_user_entity")
    val name: String? = null,
)

object TableUser {
    const val tableNameUser = "user"
}