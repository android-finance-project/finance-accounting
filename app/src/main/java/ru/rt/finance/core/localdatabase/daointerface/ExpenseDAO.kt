package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import ru.rt.finance.features.management.data.model.finance.Expense
import java.util.Date

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExpense(info: Expense)

    @Update
    suspend fun updateExpense(info: Expense)

    @Delete
    suspend fun deleteExpense(info: Expense)

    @Query("Select * From Expense WHERE ID = :id")
    suspend fun getExpenseById(id: Int): Expense?

    @Query("DELETE FROM Expense")
    suspend fun deleteAllExpense()

    @Query("SELECT exp.* FROM Expense As exp ORDER BY DATE DESC,ID DESC")
    suspend fun getInfo(): List<Expense>?

    @Query("SELECT * FROM  Expense  WHERE sum LIKE :searchQuery ")
    suspend fun searchDatabaseSum(searchQuery: String): List<Expense>?

    @Query("SELECT * FROM  Expense  WHERE date BETWEEN   :searchDateBegin AND  :searchDateEnd")
    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date): List<Expense>?
}

