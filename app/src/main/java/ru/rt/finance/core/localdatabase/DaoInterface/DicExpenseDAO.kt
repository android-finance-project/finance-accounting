package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.features.dictonary.data.model.dictionary.Dictionary

@Dao
interface DicExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDicExpense(info: DicExpenseEntity)

    @Update
    suspend fun updateDicExpense(info: DicExpenseEntity)

    @Delete
    suspend fun deleteDicExpense(info: DicExpenseEntity)

    @Query("Select * From ${Dictionary.tableNameDicExpense} WHERE ID = :id")
    suspend fun getDicExpenseById(id: Int): DicExpenseEntity?

    // Dangerous function )))
    @Query("DELETE FROM  ${Dictionary.tableNameDicExpense}")
    suspend fun deleteAllDicExpense()

    @Query("SELECT t1.* FROM ${Dictionary.tableNameDicExpense} As t1  ORDER BY name ASC")
    suspend fun getInfo(): List<DicExpenseEntity>?

    @Query("SELECT * FROM  ${Dictionary.tableNameDicExpense}  WHERE name LIKE '%' || :searchQuery || '%' ")
    suspend fun searchByName(searchQuery: String): List<DicExpenseEntity>?
}