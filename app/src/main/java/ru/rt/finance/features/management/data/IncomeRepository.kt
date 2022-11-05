package ru.rt.finance.features.management.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.rt.finance.core.localdatabase.daointerface.IncomeDao
import ru.rt.finance.features.management.data.model.finance.IncomeEntity

class IncomeRepository(private val incomeSource: IncomeDao, private val dispatcher: CoroutineDispatcher) {

    suspend fun addIncome(info: IncomeEntity) {
        withContext(dispatcher) {
            incomeSource.addIncome(info)
        }
    }

    suspend fun updateIncome(info: IncomeEntity) {
        withContext(dispatcher) {
            incomeSource.updateIncome(info)
        }
    }

    suspend fun getIncomeById(id: Int) {
        withContext(dispatcher) {
            incomeSource.getIncomeById(id)
        }
    }

    suspend fun deleteIncome(info: IncomeEntity) {
        withContext(dispatcher) {
            incomeSource.deleteIncome(info)
        }
    }

    suspend fun getByDates(searchDateBegin: String, searchDateEnd: String) {
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
