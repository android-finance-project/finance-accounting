package ru.rt.finance.features.dictonary.data.localdb

import ru.rt.finance.core.localdatabase.daointerface.DicIncomeDAO
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome

class DicIncome(private val dicIncomeDAO: DicIncomeDAO) {

    suspend fun getInfo() {
        dicIncomeDAO.getInfo()
    }

    suspend fun addDicIncome(info: DicIncome) {
        dicIncomeDAO.addDicIncome(info)
    }

    suspend fun updateDicIncome(info: DicIncome) {
        dicIncomeDAO.updateDicIncome(info)
    }

    suspend fun deleteDicIncome(info: DicIncome) {
        dicIncomeDAO.deleteDicIncome(info)
    }

    suspend fun deleteAllDicIncome() {
        dicIncomeDAO.deleteAllDicIncome()
    }

    suspend fun searchByName(searchQuery: String): List<DicIncome>? {
        return dicIncomeDAO.searchByName(searchQuery)
    }
}
