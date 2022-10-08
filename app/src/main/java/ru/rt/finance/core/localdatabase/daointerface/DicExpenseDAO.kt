package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense
import ru.rt.finance.features.dictonary.data.model.dictionary.Dictionary

@Dao
interface DicExpenseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDicExpense(info: DicExpense)

    @Update
    suspend fun updateDicExpense(info: DicExpense)

    @Delete
    suspend fun deleteDicExpense(info: DicExpense)

    @Query("Select * From ${Dictionary.tableNameDicExpense} WHERE ID = :id")
    suspend fun getDicExpenseById(id: Int): DicExpense?

    // Dangerous function )))
    @Query("DELETE FROM  ${Dictionary.tableNameDicExpense}")
    suspend fun deleteAllDicExpense()

    @Query("SELECT t1.* FROM ${Dictionary.tableNameDicExpense} As t1  ORDER BY name ASC")
    suspend fun getInfo(): List<DicExpense>?

    @Query("SELECT * FROM  ${Dictionary.tableNameDicExpense}  WHERE name LIKE :searchQuery")
    suspend fun searchByName(searchQuery: String): List<DicExpense>?
}
