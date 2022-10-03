package ru.rt.finance.core.localdatabase

//Packages cannot be imported
//Packages cannot be imported

import ru.rt.finance.features.user.data.model.User
import ru.rt.finance.features.management.data.model.finance.*
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.rt.finance.core.localdatabase.DaoInterface.*
import ru.rt.finance.core.localdatabase.ParametersDB.DATA_DB
import ru.rt.finance.core.localdatabase.ParametersDB.VERSION
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome

@Database(entities = [User::class,DicExpense::class,DicIncome::class,Expense::class,Income::class],version = VERSION,exportSchema = false)

abstract class FinanceInstantDatabase: RoomDatabase() {

    abstract fun income(): IncomeDao
    abstract fun expense(): ExpenseDao
    abstract fun dicIncome(): DicIncomeDAO
    abstract fun dicExpense(): DicExpenseDAO
    abstract fun user(): UserDAO


    companion object{
        @Volatile
        //todo - засунуть в di
        private var INSTANCE : FinanceInstantDatabase? = null

        fun getDataBase(context: Context):FinanceInstantDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FinanceInstantDatabase::class.java,
                        DATA_DB)
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}

