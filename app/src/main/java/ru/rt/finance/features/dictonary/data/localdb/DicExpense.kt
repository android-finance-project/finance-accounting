package ru.rt.finance.features.dictonary.data.localdb

import ru.rt.finance.core.localdatabase.daointerface.DicExpenseDAO
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense

class DicExpense(private val dicExpenseDAO: DicExpenseDAO) {

    suspend fun getInfo() {
        dicExpenseDAO.getInfo()
    }

    suspend fun addDicExpense(info: DicExpense) {
        dicExpenseDAO.addDicExpense(info)
    }

    suspend fun updateDicExpense(info: DicExpense) {
        dicExpenseDAO.updateDicExpense(info)
    }

    suspend fun deleteDicExpense(info: DicExpense) {
        dicExpenseDAO.deleteDicExpense(info)
    }

    suspend fun getDicExpenseById(id: Int) {
        dicExpenseDAO.getDicExpenseById(id)
    }

    suspend fun deleteAllDicExpense() {
        dicExpenseDAO.deleteAllDicExpense()
    }

    suspend fun searchByName(searchQuery: String): List<DicExpense>? {
        return dicExpenseDAO.searchByName(searchQuery)
    }
}
