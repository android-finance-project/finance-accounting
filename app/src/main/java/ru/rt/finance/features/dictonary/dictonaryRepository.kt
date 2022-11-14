package ru.rt.finance.features.dictonary

import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineDispatcher
import ru.rt.finance.core.localdatabase.daointerface.DicExpenseDao
import ru.rt.finance.core.localdatabase.daointerface.DicIncomeDao
import ru.rt.finance.core.utils.SORT
import ru.rt.finance.core.utils.SORT.ASC
import ru.rt.finance.core.utils.SORT.DESC
import ru.rt.finance.core.utils.SORT.NONE
import ru.rt.finance.core.utils.safeUnitCall
import ru.rt.finance.features.dictonary.DicIncomeRepository.Companion.DELTA_INCREMENT
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncomeEntity

class DicExpenseRepository(private val dicExpenseSource: DicExpenseDao, private val dispatcher: CoroutineDispatcher) {

    suspend fun getInfo(sort: SORT): List<DicExpenseEntity>? = withContext(dispatcher)
    {
        when (sort) {
            ASC -> {
                dicExpenseSource.getInfoAsc()
            }
            DESC -> {
                dicExpenseSource.getInfoDesc()
            }
            NONE -> {
                dicExpenseSource.getInfoNone()
            }
            else -> {
                dicExpenseSource.getInfoNone()
            }
        }

    }

    suspend fun addDicExpense(info: DicExpenseEntity) {
        withContext(dispatcher) {
            dicExpenseSource.addDicExpense(info)
        }
    }

    suspend fun addDicExpenseResult(info: DicExpenseEntity): Result<Unit> {
        val ret: Result<Unit>
        withContext(dispatcher) {
            ret = safeUnitCall { dicExpenseSource.addDicExpense(info) }
        }
        return ret
    }

    suspend fun getLastKey(): Int = withContext(dispatcher) {
        dicExpenseSource.getLastKey() + DELTA_INCREMENT
    }

    suspend fun addDicExpenseAll(vararg info: DicExpenseEntity) {
        withContext(dispatcher) {
            dicExpenseSource.addDicExpenseAll(*info)
        }
    }

    suspend fun updateDicExpense(info: DicExpenseEntity) {
        withContext(dispatcher) {
            dicExpenseSource.updateDicExpense(info)
        }
    }

    suspend fun updateDicExpenseResult(info: DicExpenseEntity): Result<Unit> {
        val ret: Result<Unit>
        withContext(dispatcher) {
            ret = safeUnitCall { dicExpenseSource.updateDicExpense(info) }
        }
        return ret
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

    suspend fun getInfo(): List<DicIncomeEntity>? {
        val ret: List<DicIncomeEntity>?
        withContext(dispatcher) {
            ret = dicIncomeSource.getInfo()

        }
        return ret
    }

    suspend fun addDicIncome(info: DicIncomeEntity) {
        withContext(dispatcher) {
            dicIncomeSource.addDicIncome(info)
        }
    }

    suspend fun addDicIncomeAll(vararg info: DicIncomeEntity) {
        withContext(dispatcher) {
            dicIncomeSource.addDicIncomeAll(*info)
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

    companion object {
        const val DELTA_INCREMENT: Int = 1
    }
}