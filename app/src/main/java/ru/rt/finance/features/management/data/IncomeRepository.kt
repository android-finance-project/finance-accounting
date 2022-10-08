package ru.rt.finance.features.management.data

import java.util.Date
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.rt.finance.core.localdatabase.daointerface.IncomeDao
import ru.rt.finance.features.management.data.model.finance.Income

class IncomeRepository(private val incomeSource: IncomeDao, private val dispatcher: CoroutineDispatcher) {

    suspend fun addIncome(info: Income) {
        withContext(dispatcher) {
            incomeSource.addIncome(info)
        }
    }

    suspend fun updateIncome(info: Income) {
        withContext(dispatcher) {
            incomeSource.updateIncome(info)
        }
    }

    suspend fun getIncomeById(id: Int) {
        withContext(dispatcher) {
            incomeSource.getIncomeById(id)
        }
    }

    suspend fun deleteIncome(info: Income) {
        withContext(dispatcher) {
            incomeSource.deleteIncome(info)
        }
    }

    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date) {
        withContext(dispatcher) {
            incomeSource.getByDates(searchDateBegin, searchDateEnd)
        }
    }

    suspend fun searchDatabaseSum(searchQuery: String) {
        withContext(dispatcher) {
            incomeSource.searchDatabaseSum(searchQuery)
        }
    }
}
