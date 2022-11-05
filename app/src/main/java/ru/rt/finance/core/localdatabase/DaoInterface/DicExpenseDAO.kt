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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDicExpenseAll(vararg info: DicExpenseEntity)

    @Update
    suspend fun updateDicExpense(info: DicExpenseEntity)

    @Delete
    suspend fun deleteDicExpense(info: DicExpenseEntity)

    @Query("Select * From ${Dictionary.tableNameDicExpense} WHERE id_dic_expense_entity = :id")
    suspend fun getDicExpenseById(id: Int): DicExpenseEntity?

    // Dangerous function )))
    @Query("DELETE FROM  ${Dictionary.tableNameDicExpense}")
    suspend fun deleteAllDicExpense()

    @Query("SELECT t1.* FROM ${Dictionary.tableNameDicExpense} As t1  ORDER BY name_dic_expense_entity ASC")
    suspend fun getInfo(): List<DicExpenseEntity>?

    @Query("SELECT * FROM  ${Dictionary.tableNameDicExpense}  WHERE name_dic_expense_entity LIKE '%' || :searchQuery || '%' ")
    suspend fun searchByName(searchQuery: String): List<DicExpenseEntity>?

    @Query("SELECT t1.id_dic_expense_entity FROM ${Dictionary.tableNameDicExpense} As t1  ORDER BY id_dic_expense_entity DESC LIMIT 1")
    suspend fun getLastKey(): Int
}