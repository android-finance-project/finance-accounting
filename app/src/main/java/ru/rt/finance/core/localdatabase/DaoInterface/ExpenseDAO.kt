package ru.rt.finance.core.localdatabase.DaoInterface

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome
import ru.rt.finance.features.dictonary.data.model.dictionary.Dictonary
import ru.rt.finance.features.management.data.model.finance.Expense
import ru.rt.finance.features.user.data.localdb.User
import java.util.*

@Dao
 interface ExpenseDao {
        //@Insert(onConflict = OnConflictStrategy.IGNORE)
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun addExpense(info : Expense)
        //: Completable - rx что ли попробывать
        @Update
        fun updateExpense(info: Expense)

        @Delete
        fun deleteExpense(info: Expense)

       @Query("Select * From Expense WHERE ID = :id")
       suspend fun getExpenseById(id: Int): Expense?


        @Query("DELETE FROM Expense")
        fun deleteAllExpense()

        // @Query("SELECT * FROM Income ORDER BY Id ASC")
        @Query("SELECT exp.* FROM Expense As exp ORDER BY DATE ASC,ID DESC")
        fun getInfo(): List<Expense>?

        @Query("SELECT * FROM  Expense  WHERE sum LIKE :searchQuery ")
        fun searchDatabaseSum(searchQuery:String): Flow<List<Expense>?>

       @Query("SELECT * FROM  Expense  WHERE date BETWEEN   :searchDateBegin AND  :searchDateEnd")
       fun searchDatabaseDates(searchDateBegin: Date, searchDateEnd: Date): Flow<List<Expense>?>


}

