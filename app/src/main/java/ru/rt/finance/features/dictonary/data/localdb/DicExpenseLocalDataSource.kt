package ru.rt.finance.features.dictonary.data.localdb

import ru.rt.finance.core.localdatabase.daointerface.DicExpenseDao
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity

class DicExpenseLocalDataSource(private val dicExpenseDao: DicExpenseDao) {

    suspend fun getInfo() {
        dicExpenseDao.getInfo()
    }

    suspend fun addDicExpense(info: DicExpenseEntity) {
        dicExpenseDao.addDicExpense(info)
    }

    suspend fun updateDicExpense(info: DicExpenseEntity) {
        dicExpenseDao.updateDicExpense(info)
    }

    suspend fun deleteDicExpense(info: DicExpenseEntity) {
        dicExpenseDao.deleteDicExpense(info)
    }

    suspend fun getDicExpenseById(id: Int): DicExpenseEntity? {
        return dicExpenseDao.getDicExpenseById(id)
    }

    suspend fun deleteAllDicExpense() {
        dicExpenseDao.deleteAllDicExpense()
    }

    suspend fun searchByName(searchQuery: String): List<DicExpenseEntity>? {
        return dicExpenseDao.searchByName(searchQuery)
    }
}
