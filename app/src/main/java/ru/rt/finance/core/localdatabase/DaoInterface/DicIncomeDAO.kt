package ru.rt.finance.core.localdatabase.DaoInterface

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome
import ru.rt.finance.features.dictonary.data.model.dictionary.Dictonary
import ru.rt.finance.features.management.data.model.finance.Expense



@Dao
interface DicIncomeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDicIncome(info: DicIncome)
    @Update
    suspend  fun updateDicIncome(info: DicIncome)

    @Delete
    fun deleteDicIncome(info: DicIncome)
    //: Integer

    @Query("Select * From ${Dictonary.tableNameDicIncome} WHERE ID = :id")
    suspend fun getDicIncomeById(id: Int): DicIncome?


    // Dangerous function )))
    @Query("DELETE FROM  ${Dictonary.tableNameDicIncome}")
    fun deleteAllDicIncome()

    @Query("SELECT t1.* FROM ${Dictonary.tableNameDicIncome} As t1 ORDER BY NAME ASC")
    fun getInfo(): List<DicIncome>?

    @Query("SELECT * FROM  ${Dictonary.tableNameDicIncome}  WHERE name LIKE :searchQuery ")
    fun searchDatabase(searchQuery:String): Flow<List<DicIncome>?>

}

