package ru.rt.finance.core.localdatabase.DaoInterface

import ru.rt.finance.features.management.data.model.finance.Income

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.rt.finance.features.management.data.model.finance.Expense
import java.util.*

@Dao
interface IncomeDao {
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIncome(info: Income)
    //: Completable - rx что ли попробывать
    @Update
    fun updateIncome(info: Income)

    @Delete
    fun deleteIncome(info: Income)

    @Query("Select * From Expense WHERE ID = :id")
    suspend fun getIncomeById(id: Int): Income?


    @Query("DELETE FROM Income")
    fun deleteAllIncome()


    @Query("SELECT inc.* FROM Income As inc ORDER BY  DATE ASC,ID DESC")
    fun getInfo():List<Income>

    @Query("SELECT * FROM  Income  WHERE sum LIKE :searchQuery ")
    fun searchDatabaseSum(searchQuery:String): Flow<List<Income>?>

    @Query("SELECT * FROM  Income  WHERE date BETWEEN   :searchDateBegin AND  :searchDateEnd")
    fun searchDatabaseDates(searchDateBegin: Date, searchDateEnd: Date): Flow<List<Income>?>


}