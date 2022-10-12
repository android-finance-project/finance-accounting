package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncomeEntity
import ru.rt.finance.features.dictonary.data.model.dictionary.Dictionary

@Dao
interface DicIncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDicIncome(info: DicIncomeEntity)

    @Update
    suspend fun updateDicIncome(info: DicIncomeEntity)

    @Delete
    suspend fun deleteDicIncome(info: DicIncomeEntity)

    @Query("Select * From ${Dictionary.tableNameDicIncome} WHERE ID = :id")
    suspend fun getDicIncomeById(id: Int): DicIncomeEntity?

    // Dangerous function )))
    @Query("DELETE FROM  ${Dictionary.tableNameDicIncome}")
    suspend fun deleteAllDicIncome()

    @Query("SELECT t1.* FROM ${Dictionary.tableNameDicIncome} As t1 ORDER BY NAME ASC")
    suspend fun getInfo(): List<DicIncomeEntity>?

    @Query("SELECT * FROM  ${Dictionary.tableNameDicIncome}  WHERE name LIKE '%' ||  :searchQuery || '%' ")
    suspend fun searchByName(searchQuery: String): List<DicIncomeEntity>?
}