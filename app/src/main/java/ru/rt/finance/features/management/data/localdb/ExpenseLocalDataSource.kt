package ru.rt.finance.features.management.data.localdb

import java.util.Date
import ru.rt.finance.core.localdatabase.daointerface.ExpenseDao
import ru.rt.finance.features.management.data.model.finance.Expense

class ExpenseLocalDataSource(private val expenseDAO: ExpenseDao) {

    suspend fun getInfo() {
        expenseDAO.getInfo()
    }

    suspend fun addExpense(info: Expense) {
        expenseDAO.addExpense(info)
    }

    suspend fun updateExpense(info: Expense) {
        expenseDAO.updateExpense(info)
    }

    suspend fun deleteExpense(info: Expense) {
        expenseDAO.deleteExpense(info)
    }

    suspend fun deleteAllExpense() {
        expenseDAO.deleteAllExpense()
    }

    suspend fun searchDatabaseSum(searchQuery: String): List<Expense>? {
        return expenseDAO.searchDatabaseSum(searchQuery)
    }

    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date): List<Expense>? {
        return expenseDAO.getByDates(searchDateBegin, searchDateEnd)
    }
}
