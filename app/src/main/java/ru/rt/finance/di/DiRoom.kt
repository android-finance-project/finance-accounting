package ru.rt.finance.di

import androidx.room.Room
import org.koin.dsl.module
import ru.rt.finance.core.localdatabase.FinanceInstantDatabase
import ru.rt.finance.core.localdatabase.ParametersDB.DATA_DB
import android.content.Context
import org.koin.android.ext.koin.androidContext

class DiRoom {

    private fun provideDataBase(context: Context): FinanceInstantDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FinanceInstantDatabase::class.java,
            DATA_DB
        ).build()
    }

    val roomModule = module {
        single { provideDataBase(androidContext()) }
        single { get<FinanceInstantDatabase>().dicExpense() }
        single { get<FinanceInstantDatabase>().dicIncome() }
        single { get<FinanceInstantDatabase>().expense() }
        single { get<FinanceInstantDatabase>().income() }
    }
}
