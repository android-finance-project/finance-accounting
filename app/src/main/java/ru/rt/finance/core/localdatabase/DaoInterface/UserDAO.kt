package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import ru.rt.finance.features.user.data.model.TableUser
import ru.rt.finance.features.user.data.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(info: UserEntity)

    @Update
    suspend fun updateUser(info: UserEntity)

    @Delete
    suspend fun deleteUser(info: UserEntity)

    @Query("Select * From ${TableUser.tableNameUser} WHERE ID = :id")
    suspend fun getUserById(id: Int): UserEntity?

    @Query("DELETE FROM ${TableUser.tableNameUser}")
    suspend fun deleteAllUser()

    @Query("SELECT t1.* FROM ${TableUser.tableNameUser} As t1 order by id asc")
    suspend fun getInfo(): List<UserEntity>?

    @Query("SELECT * FROM ${TableUser.tableNameUser}  WHERE name LIKE '%' ||  :searchQuery || '%'")
    suspend fun searchByName(searchQuery: String): List<UserEntity>?
}