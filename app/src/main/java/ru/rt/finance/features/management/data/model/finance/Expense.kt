package ru.rt.finance.features.management.data.model.finance

import ru.rt.finance.features.management.data.model.dictionary.DicExpense
import ru.rt.finance.features.user.data.model.User
import java.time.LocalDate

/**
 * Расход
 */
data class Expense(

    /**
     * PK
     */
    val id: Int,

    /**
     * Пользователь (дефолтный по умолчанию)
     */
    val user: User,

    /**
     * Категория
     */
    val category: DicExpense? = null,

    /**
     * Дата
     */
    val date: LocalDate = LocalDate.now(),

    /**
     * Сумма (в копейках)
     */
    val sum: Long,

    /**
     * Комментарий
     */
    val comment: String? = null,
)