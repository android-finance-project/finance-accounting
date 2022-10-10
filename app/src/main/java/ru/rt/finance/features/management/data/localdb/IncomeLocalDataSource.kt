package ru.rt.finance.features.management.data.localdb

import ru.rt.finance.core.localdatabase.daointerface.IncomeDao
import ru.rt.finance.features.management.data.model.finance.Income
import java.util.Date

class IncomeLocalDataSource(private val incomeDAO: IncomeDao) {

    suspend fun getInfo() {
        incomeDAO.getInfo()
    }

    suspend fun addIncome(info: Income) {
        incomeDAO.addIncome(info)
    }

    suspend fun updateIncome(info: Income) {
        incomeDAO.updateIncome(info)
    }

    suspend fun deleteIncome(info: Income) {
        incomeDAO.deleteIncome(info)
    }

    suspend fun deleteAllIncome() {
        incomeDAO.deleteAllIncome()
    }

    suspend fun searchDatabaseSum(searchQuery: String): List<Income>? {
        return incomeDAO.searchDatabaseSum(searchQuery)
    }

    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date): List<Income>? {
        return incomeDAO.getByDates(searchDateBegin, searchDateEnd)
    }
}