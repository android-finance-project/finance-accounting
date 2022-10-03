package ru.rt.finance.features.management.data.localdb
import kotlinx.coroutines.flow.Flow
import ru.rt.finance.core.localdatabase.DaoInterface.ExpenseDao
import ru.rt.finance.features.management.data.model.finance.Expense
import java.util.*


class Expense(private val ExpenseDAO : ExpenseDao) {


    val ExpenseInfo: /*LiveData<*/List<Expense>? = ExpenseDAO.getInfo()

    suspend fun addExpense(info : Expense){
        ExpenseDAO.addExpense(info)
    }

    suspend fun updateExpense(info : Expense){
        ExpenseDAO.updateExpense(info)
    }

    suspend fun deleteExpense(info: Expense){
        ExpenseDAO.deleteExpense(info)
    }

    suspend fun deleteAllExpense(){
        ExpenseDAO.deleteAllExpense()
    }

    fun searchDatabaseExpense(searchQuery: String): Flow<List<Expense>?> {
        return ExpenseDAO.searchDatabaseSum(searchQuery)
    }

    // Из какой библиотеки нужно дату брать ?
    fun searchDatabaseExpense(searchDateBegin: Date,searchDateEnd: Date): Flow<List<Expense>?> {
        return ExpenseDAO.searchDatabaseDates(searchDateBegin,searchDateEnd) }


    }
