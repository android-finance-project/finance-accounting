package ru.rt.finance.features.management.data

import java.util.Date
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.rt.finance.core.localdatabase.daointerface.ExpenseDao
import ru.rt.finance.features.management.data.model.finance.Expense

class ExpenseRepository(private val expenseSource: ExpenseDao, private val dispatcher: CoroutineDispatcher) {

    suspend fun addExpense(info: Expense) {
        withContext(dispatcher) {
            expenseSource.addExpense(info)
        }
    }

    suspend fun updateExpense(info: Expense) {
        withContext(dispatcher) {
            expenseSource.updateExpense(info)
        }
    }

    suspend fun getExpenseById(id: Int) {
        withContext(dispatcher) {
            expenseSource.getExpenseById(id)
        }
    }

    suspend fun deleteExpense(info: Expense) {
        withContext(dispatcher) {
            expenseSource.deleteExpense(info)
        }
    }

    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date) {
        withContext(dispatcher) {
            expenseSource.getByDates(searchDateBegin, searchDateEnd)
        }
    }

    suspend fun searchDatabaseSum(searchQuery: String) {
        withContext(dispatcher) {
            expenseSource.searchDatabaseSum(searchQuery)
        }
    }
}
