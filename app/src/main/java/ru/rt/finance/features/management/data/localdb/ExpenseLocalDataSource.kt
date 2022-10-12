package ru.rt.finance.features.management.data.localdb

import java.util.Date
import ru.rt.finance.core.localdatabase.daointerface.ExpenseDao
import ru.rt.finance.features.management.data.model.finance.ExpenseEntity

class ExpenseLocalDataSource(private val expenseDao: ExpenseDao) {

    suspend fun getInfo() {
        expenseDao.getInfo()
    }

    suspend fun addExpense(info: ExpenseEntity) {
        expenseDao.addExpense(info)
    }

    suspend fun updateExpense(info: ExpenseEntity) {
        expenseDao.updateExpense(info)
    }

    suspend fun deleteExpense(info: ExpenseEntity) {
        expenseDao.deleteExpense(info)
    }

    suspend fun getExpenseById(id: Int): ExpenseEntity? {
        return expenseDao.getExpenseById(id)
    }

    suspend fun deleteAllExpense() {
        expenseDao.deleteAllExpense()
    }

    suspend fun searchDatabaseSum(searchQuery: String): List<ExpenseEntity>? {
        return expenseDao.searchDatabaseSum(searchQuery)
    }

    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date): List<ExpenseEntity>? {
        return expenseDao.getByDates(searchDateBegin, searchDateEnd)
    }
}
