package ru.rt.finance.features.dictonary.data.localdb

import ru.rt.finance.core.localdatabase.daointerface.DicIncomeDao
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncomeEntity

class DicIncomeLocalDataSource(private val dicIncomeDao: DicIncomeDao) {

    suspend fun getInfo() {
        dicIncomeDao.getInfo()
    }

    suspend fun addDicIncome(info: DicIncomeEntity) {
        dicIncomeDao.addDicIncome(info)
    }

    suspend fun addDicIncomeAll(vararg info: DicIncomeEntity) {
        dicIncomeDao.addDicIncomeAll(*info)
    }

    suspend fun updateDicIncome(info: DicIncomeEntity) {
        dicIncomeDao.updateDicIncome(info)
    }

    suspend fun deleteDicIncome(info: DicIncomeEntity) {
        dicIncomeDao.deleteDicIncome(info)
    }

    suspend fun getDicIncomeById(id: Int): DicIncomeEntity? {
        return dicIncomeDao.getDicIncomeById(id)
    }

    suspend fun deleteAllDicIncome() {
        dicIncomeDao.deleteAllDicIncome()
    }

    suspend fun searchByName(searchQuery: String): List<DicIncomeEntity>? {
        return dicIncomeDao.searchByName(searchQuery)
    }
}