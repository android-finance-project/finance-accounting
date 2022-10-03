package ru.rt.finance.core.localdatabase.DaoInterface

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.rt.finance.features.management.data.model.finance.Expense
import ru.rt.finance.features.management.data.model.finance.Income
import ru.rt.finance.features.user.data.model.User

@Dao
interface UserDAO {
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(info: User)
    //: Completable - rx что ли попробывать
    @Update
    fun updateUser(info: User)

    @Delete
    fun deleteUser(info: User)

    @Query("Select * From User WHERE ID = :id")
    suspend fun getUserById(id: Int): User?


    @Query("DELETE FROM  User")
    fun deleteAllUser()

    // @Query("SELECT * FROM Income ORDER BY Id ASC")
    @Query("SELECT t1.* FROM User As t1")
    fun getInfo(): List<User>?

    @Query("SELECT * FROM User  WHERE name LIKE :searchQuery ")
    fun searchDatabase(searchQuery:String): Flow<List<User>?>
}