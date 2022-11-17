package ru.rt.finance.features.dictonary.data.localdb

import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.core.localdatabase.daointerface.DicExpenseDao


class DicExpenseLocalDataSource(private val dicExpenseDao: DicExpenseDao) {

    suspend fun getInfoNone() {
        dicExpenseDao.getInfoNone()
    }

    suspend fun getInfoAsc() {
        dicExpenseDao.getInfoAsc()
    }

    suspend fun getInfoDesc() {
        dicExpenseDao.getInfoDesc()
    }

    suspend fun addDicExpense(info: DicExpenseEntity) {
        dicExpenseDao.addDicExpense(info)
    }

    suspend fun addDicExpenseAll(vararg info: DicExpenseEntity) {
        dicExpenseDao.addDicExpenseAll(*info)
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
