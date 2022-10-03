package ru.rt.finance.features.management.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.rt.finance.core.localdatabase.DaoInterface.ExpenseDao
import ru.rt.finance.core.localdatabase.DaoInterface.IncomeDao
import ru.rt.finance.features.management.data.model.finance.Expense
import ru.rt.finance.features.management.data.model.finance.Income
import java.util.*

class ManagementRepository {

    class ExpenseRepository(private val expenseSource: ExpenseDao, private val dispatcher : CoroutineDispatcher) {
        //ф-ция считывания

        suspend fun addExpense(info: Expense) {
            withContext(dispatcher) {
                expenseSource.addExpense(info)
            }
        }

        suspend fun updateExpense(info: Expense) {
            withContext(dispatcher) {
                expenseSource.updateExpense(info)
            }
        }

        suspend fun getExpenseById(id: Int) {
            withContext(dispatcher) {
                expenseSource.getExpenseById(id)
            }
        }


        suspend fun deleteExpense(info: Expense) {
            withContext(dispatcher) {
                expenseSource.deleteExpense(info)
            }
        }


        suspend fun searchDatabaseDates(searchDateBegin: Date, searchDateEnd: Date) {
            withContext(dispatcher) {
                expenseSource.searchDatabaseDates(searchDateBegin, searchDateEnd)
            }
        }


        suspend fun searchDatabaseSum(searchQuery : String) {
            withContext(dispatcher) {
                expenseSource.searchDatabaseSum(searchQuery)
            }
        }


    }


        class IncomeRepository(private val incomeSource: IncomeDao, private val dispatcher : CoroutineDispatcher) {
            //ф-ция считывания

            suspend fun addIncome(info: Income) {
                withContext(dispatcher) {
                    incomeSource.addIncome(info)
                }
            }

            suspend fun updateIncome(info: Income) {
                withContext(dispatcher) {
                    incomeSource.updateIncome(info)
                }
            }

            suspend fun getIncomeById(id: Int) {
                withContext(dispatcher) {
                    incomeSource.getIncomeById(id)
                }
            }


                suspend fun deleteIncome(info: Income) {
                withContext(dispatcher) {
                    incomeSource.deleteIncome(info)
                }
            }

            suspend fun searchDatabaseDates(searchDateBegin : Date, searchDateEnd : Date) {
                withContext(dispatcher) {
                    incomeSource.searchDatabaseDates(searchDateBegin, searchDateEnd)
                }
            }

            suspend fun searchDatabaseSum(searchQuery : String) {
                withContext(dispatcher) {
                    incomeSource.searchDatabaseSum(searchQuery)
                }
            }

        }



    }