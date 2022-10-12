package ru.rt.finance.features.dictonary

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.rt.finance.core.localdatabase.daointerface.DicExpenseDao
import ru.rt.finance.core.localdatabase.daointerface.DicIncomeDao
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncomeEntity

class DicExpenseRepository(private val dicExpenseSource: DicExpenseDao, private val dispatcher: CoroutineDispatcher) {

    suspend fun addDicExpense(info: DicExpenseEntity) {
        withContext(dispatcher) {
            dicExpenseSource.addDicExpense(info)
        }
    }

    suspend fun updateDicExpense(info: DicExpenseEntity) {
        withContext(dispatcher) {
            dicExpenseSource.updateDicExpense(info)
        }
    }

    suspend fun getDicExpenseById(id: Int) {
        withContext(dispatcher) {
            dicExpenseSource.getDicExpenseById(id)
        }
    }

    suspend fun deleteDicExpense(info: DicExpenseEntity) {
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

class DicIncomeRepository(private val dicIncomeSource: DicIncomeDao, private val dispatcher: CoroutineDispatcher) {

    suspend fun addDicIncome(info: DicIncomeEntity) {
        withContext(dispatcher) {
            dicIncomeSource.addDicIncome(info)
        }
    }

    suspend fun updateDicIncome(info: DicIncomeEntity) {
        withContext(dispatcher) {
            dicIncomeSource.updateDicIncome(info)
        }
    }

    suspend fun getDicIncomeById(id: Int) {
        withContext(dispatcher) {
            dicIncomeSource.getDicIncomeById(id)
        }
    }

    suspend fun deleteDicIncome(info: DicIncomeEntity) {
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

