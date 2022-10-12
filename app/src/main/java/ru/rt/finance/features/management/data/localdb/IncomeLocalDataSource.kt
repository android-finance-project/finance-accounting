package ru.rt.finance.features.management.data.localdb

import ru.rt.finance.core.localdatabase.daointerface.IncomeDao
import java.util.Date
import ru.rt.finance.features.management.data.model.finance.IncomeEntity

class IncomeLocalDataSource(private val incomeDao: IncomeDao) {

    suspend fun getInfo() {
        incomeDao.getInfo()
    }

    suspend fun addIncome(info: IncomeEntity) {
        incomeDao.addIncome(info)
    }

    suspend fun updateIncome(info: IncomeEntity) {
        incomeDao.updateIncome(info)
    }

    suspend fun deleteIncome(info: IncomeEntity) {
        incomeDao.deleteIncome(info)
    }

    suspend fun getIncomeById(id: Int): IncomeEntity? {
        return  incomeDao.getIncomeById(id)
    }

    suspend fun deleteAllIncome() {
        incomeDao.deleteAllIncome()
    }

    suspend fun searchDatabaseSum(searchQuery: String): List<IncomeEntity>? {
        return incomeDao.searchDatabaseSum(searchQuery)
    }

    suspend fun getByDates(searchDateBegin: Date, searchDateEnd: Date): List<IncomeEntity>? {
        return incomeDao.getByDates(searchDateBegin, searchDateEnd)
    }
}