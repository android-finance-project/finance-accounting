package ru.rt.finance.features.user.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Пользователь
 *
 */
@Entity(tableName = "User")
data class User(
    /**
     *
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    /**
     *
     */
    val name: String? = null,
    //val surname: String? = null,
    //val patronimic: String? = null,
    //val flagCommandante: Int? = null,
)