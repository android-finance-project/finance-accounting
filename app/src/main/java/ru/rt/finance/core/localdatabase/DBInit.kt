package ru.rt.finance.core.localdatabase

import ru.rt.finance.features.user.data.model.User
import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rt.finance.core.localdatabase.ParametersDB.VERSION
import ru.rt.finance.core.localdatabase.daointerface.DicExpenseDAO
import ru.rt.finance.core.localdatabase.daointerface.DicIncomeDAO
import ru.rt.finance.core.localdatabase.daointerface.ExpenseDao
import ru.rt.finance.core.localdatabase.daointerface.IncomeDao
import ru.rt.finance.core.localdatabase.daointerface.UserDAO
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome
import ru.rt.finance.features.management.data.model.finance.Expense
import ru.rt.finance.features.management.data.model.finance.Income

@Database(
    entities = [User::class, DicExpense::class, DicIncome::class, Expense::class, Income::class],
    version = VERSION,
    exportSchema = false
)

abstract class FinanceInstantDatabase : RoomDatabase() {

    abstract fun income(): IncomeDao
    abstract fun expense(): ExpenseDao
    abstract fun dicIncome(): DicIncomeDAO
    abstract fun dicExpense(): DicExpenseDAO
    abstract fun user(): UserDAO
}
