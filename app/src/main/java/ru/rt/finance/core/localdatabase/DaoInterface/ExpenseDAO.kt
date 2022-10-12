package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import ru.rt.finance.features.management.data.model.finance.ExpenseEntity
import java.util.Date
import ru.rt.finance.features.management.data.model.finance.TableExpense

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExpense(info: ExpenseEntity)

    @Update
    suspend fun updateExpense(info: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(info: ExpenseEntity)

    @Query("Select * From ${TableExpense.tableNameExpense} WHERE ID = :id")
    suspend fun getExpenseById(id: Int): ExpenseEntity?

    @Query("DELETE FROM ${TableExpense.tableNameExpense}")
    suspend fun deleteAllExpense()

    @Query("SELECT exp.* FROM ${TableExpense.tableNameExpense} As exp ORDER BY DATE DESC,ID DESC")
    suspend fun getInfo(): List<ExpenseEntity>?

    @Query("SELECT * FROM   ${TableExpense.tableNameExpense}  WHERE sum LIKE '%' || :searchQuery || '%'")
    suspend fun searchDatabaseSum(searchQuery: String): List<ExpenseEntity>?

    @Query("SELECT * FROM   ${TableExpense.tableNameExpense}  WHERE date BETWEEN  :searchDateBegin AND  :searchDateEnd")
    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date): List<ExpenseEntity>?
}