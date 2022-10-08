package ru.rt.finance.core.localdatabase.daointerface

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome
import ru.rt.finance.features.dictonary.data.model.dictionary.Dictionary

@Dao
interface DicIncomeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDicIncome(info: DicIncome)

    @Update
    suspend fun updateDicIncome(info: DicIncome)

    @Delete
    suspend fun deleteDicIncome(info: DicIncome)

    @Query("Select * From ${Dictionary.tableNameDicIncome} WHERE ID = :id")
    suspend fun getDicIncomeById(id: Int): DicIncome?

    // Dangerous function )))
    @Query("DELETE FROM  ${Dictionary.tableNameDicIncome}")
    suspend fun deleteAllDicIncome()

    @Query("SELECT t1.* FROM ${Dictionary.tableNameDicIncome} As t1 ORDER BY NAME ASC")
    suspend fun getInfo(): List<DicIncome>?

    @Query("SELECT * FROM  ${Dictionary.tableNameDicIncome}  WHERE name LIKE :searchQuery ")
    suspend fun searchByName(searchQuery: String): List<DicIncome>?
}
