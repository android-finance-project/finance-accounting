package ru.rt.finance.features.dictonary

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.rt.finance.core.localdatabase.daointerface.DicExpenseDAO
import ru.rt.finance.core.localdatabase.daointerface.DicIncomeDAO
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome

class DicExpenseRepository(private val dicExpenseSource: DicExpenseDAO, private val dispatcher: CoroutineDispatcher) {

    suspend fun addDicExpense(info: DicExpense) {
        withContext(dispatcher) {
            dicExpenseSource.addDicExpense(info)
        }
    }

    suspend fun updateDicExpense(info: DicExpense) {
        withContext(dispatcher) {
            dicExpenseSource.updateDicExpense(info)
        }
    }

    suspend fun getDicExpenseById(id: Int) {
        withContext(dispatcher) {
            dicExpenseSource.getDicExpenseById(id)
        }
    }

    suspend fun deleteDicExpense(info: DicExpense) {
        withContext(dispatcher) {
            dicExpenseSource.deleteDicExpense(info)
        }
    }

    suspend fun searchByName(searchQuery: String) {
        withContext(dispatcher) {
            dicExpenseSource.searchByName(searchQuery)
        }
    }
}

class DicIncomeRepository(private val dicIncomeSource: DicIncomeDAO, private val dispatcher: CoroutineDispatcher) {

    suspend fun addDicIncome(info: DicIncome) {
        withContext(dispatcher) {
            dicIncomeSource.addDicIncome(info)
        }
    }

    suspend fun updateDicIncome(info: DicIncome) {
        withContext(dispatcher) {
            dicIncomeSource.updateDicIncome(info)
        }
    }

    suspend fun getDicIncomeById(id: Int) {
        withContext(dispatcher) {
            dicIncomeSource.getDicIncomeById(id)
        }
    }

    suspend fun deleteDicIncome(info: DicIncome) {
        withContext(dispatcher) {
            dicIncomeSource.deleteDicIncome(info)
        }
    }

    suspend fun searchByName(searchQuery: String) {
        withContext(dispatcher) {
            dicIncomeSource.searchByName(searchQuery)
        }
    }
}
