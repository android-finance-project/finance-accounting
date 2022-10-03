package ru.rt.finance.features.management.data.localdb

import kotlinx.coroutines.flow.Flow
import ru.rt.finance.core.localdatabase.DaoInterface.IncomeDao
import ru.rt.finance.features.management.data.model.finance.Income
import java.util.*

class Income(private val IncomeDAO : IncomeDao) {
    val ExpenseInfo: /*LiveData<*/List<Income> = IncomeDAO.getInfo()

    suspend fun addIncome(info : Income){
        IncomeDAO.addIncome(info)
    }

    suspend fun updateIncome(info : Income){
        IncomeDAO.updateIncome(info)
    }

    suspend fun deleteIncome(info: Income){
        IncomeDAO.deleteIncome(info)
    }

    suspend fun deleteAllIncome(){
        IncomeDAO.deleteAllIncome()
    }

    fun searchDatabaseIncome(searchQuery: String): Flow<List<Income>?> {
        return IncomeDAO.searchDatabaseSum(searchQuery)
    }

    // todo Из какой библиотеки нужно дату брать ?
    fun searchDatabaseIncome(searchDateBegin: Date, searchDateEnd: Date): Flow<List<Income>?> {
        return IncomeDAO.searchDatabaseDates(searchDateBegin,searchDateEnd) }


}


