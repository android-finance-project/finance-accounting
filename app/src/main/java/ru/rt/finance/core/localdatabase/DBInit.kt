package ru.rt.finance.core.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rt.finance.core.localdatabase.ParametersDB.VERSION
import ru.rt.finance.core.localdatabase.daointerface.DicExpenseDao
import ru.rt.finance.core.localdatabase.daointerface.DicIncomeDao
import ru.rt.finance.core.localdatabase.daointerface.ExpenseDao
import ru.rt.finance.core.localdatabase.daointerface.IncomeDao
import ru.rt.finance.core.localdatabase.daointerface.UserDao
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncomeEntity
import ru.rt.finance.features.management.data.model.finance.ExpenseEntity
import ru.rt.finance.features.management.data.model.finance.IncomeEntity
import ru.rt.finance.features.user.data.model.UserEntity

@Database(
    entities = [UserEntity::class, DicExpenseEntity::class, DicIncomeEntity::class, ExpenseEntity::class, IncomeEntity::class],
    version = VERSION,
    exportSchema = false
)

abstract class FinanceInstantDatabase : RoomDatabase() {

    abstract fun income(): IncomeDao
    abstract fun expense(): ExpenseDao
    abstract fun dicIncome(): DicIncomeDao
    abstract fun dicExpense(): DicExpenseDao
    abstract fun user(): UserDao
}
