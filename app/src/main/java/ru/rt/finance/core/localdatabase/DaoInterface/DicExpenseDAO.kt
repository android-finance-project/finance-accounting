package ru.rt.finance.core.localdatabase.DaoInterface

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense
import ru.rt.finance.features.dictonary.data.model.dictionary.Dictonary



@Dao
interface DicExpenseDAO {
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDicExpense(info: DicExpense)
    //: Completable - rx что ли попробывать
    @Update
    fun updateDicExpense(info: DicExpense)

    @Delete
    fun deleteDicExpense(info: DicExpense)
    //: Integer

    @Query("Select * From ${Dictonary.tableNameDicExpense} WHERE ID = :id")
    suspend fun getDicExpenseById(id: Int): DicExpense?



    // Dangerous function )))
    @Query("DELETE FROM  ${Dictonary.tableNameDicExpense}")
    fun deleteAllDicExpense()





    @Query("SELECT t1.* FROM ${Dictonary.tableNameDicExpense} As t1  ORDER BY name ASC")
    fun getInfo(): List<DicExpense>?

    @Query("SELECT * FROM  ${Dictonary.tableNameDicExpense}  WHERE name LIKE :searchQuery ")
    fun searchDatabase(searchQuery:String): Flow<List<DicExpense>?>

}
