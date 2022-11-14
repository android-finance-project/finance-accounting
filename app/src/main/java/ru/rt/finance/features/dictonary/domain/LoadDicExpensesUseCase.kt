package ru.rt.finance.features.dictonary.domain

import ru.rt.finance.features.dictonary.DicExpenseRepository
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity

class LoadDicExpensesUseCase(private val dicExpenseRepository: DicExpenseRepository) {

    suspend fun invoke(): Result<List<DicExpenseEntity>?> {
        var ret: Result<List<DicExpenseEntity>?>
        try {
            ret = Result.Companion.success(dicExpenseRepository.getInfo())
        } catch (e: Exception) {
            ret = Result.failure(e)
        }
        return ret
    }
}