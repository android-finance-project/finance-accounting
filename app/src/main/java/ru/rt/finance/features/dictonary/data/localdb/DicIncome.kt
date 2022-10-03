package ru.rt.finance.features.dictonary.data.localdb

import kotlinx.coroutines.flow.Flow
import ru.rt.finance.core.localdatabase.DaoInterface.DicIncomeDAO
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome





class DicIncome(private val DicIncomeDAO : DicIncomeDAO) {


    val DicIncomeInfo: /*LiveData<*/List<DicIncome>? = DicIncomeDAO.getInfo()

    suspend fun addDicIncome(info : DicIncome){
        DicIncomeDAO.addDicIncome(info)
    }

    suspend fun updateDicIncome(info : DicIncome){
        DicIncomeDAO.updateDicIncome(info)
    }

    suspend fun deleteDicIncome(info: DicIncome){
        DicIncomeDAO.deleteDicIncome(info)
    }

    suspend fun deleteAllDicIncome(){
        DicIncomeDAO.deleteAllDicIncome()
    }

    fun searchDatabaseDicIncome(searchQuery: String):  Flow<List<DicIncome>?> {
        return DicIncomeDAO.searchDatabase(searchQuery)}
    }

