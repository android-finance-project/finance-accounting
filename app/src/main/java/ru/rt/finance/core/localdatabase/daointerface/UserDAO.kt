package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import ru.rt.finance.features.user.data.model.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(info: User)

    @Update
    suspend fun updateUser(info: User)

    @Delete
    suspend fun deleteUser(info: User)

    @Query("Select * From User WHERE ID = :id")
    suspend fun getUserById(id: Int): User?

    @Query("DELETE FROM  User")
    suspend fun deleteAllUser()

    @Query("SELECT t1.* FROM User As t1 order by id asc")
    suspend fun getInfo(): List<User>?

    @Query("SELECT * FROM User  WHERE name LIKE :searchQuery")
    suspend fun searchByName(searchQuery: String): List<User>?
}