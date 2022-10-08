package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.rt.finance.features.management.data.model.finance.Income

import kotlinx.coroutines.flow.Flow

import java.util.Date

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIncome(info: Income)

    @Update
    suspend fun updateIncome(info: Income)

    @Delete
    suspend fun deleteIncome(info: Income)

    @Query("Select * From Expense WHERE ID = :id")
    suspend fun getIncomeById(id: Int): Income?

    @Query("DELETE FROM Income")
    suspend fun deleteAllIncome()

    @Query("SELECT inc.* FROM Income As inc ORDER BY  DATE DESC,ID DESC")
    suspend fun getInfo(): List<Income>

    @Query("SELECT * FROM  Income  WHERE sum LIKE :searchQuery ")
    suspend fun searchDatabaseSum(searchQuery: String): List<Income>?

    @Query("SELECT * FROM  Income  WHERE date BETWEEN   :searchDateBegin AND  :searchDateEnd")
    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date): List<Income>?
}