package ru.rt.finance.features.management.data.model.finance

import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncome
import ru.rt.finance.features.user.data.model.User
import java.time.LocalDate

import androidx.room.Entity
import androidx.room.PrimaryKey

/**  Таблица для оперативного учета доходов */

@Entity(tableName = "Income")
data class Income(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    /**     * Пользователь (дефолтный по умолчанию)     */
    val user: User,

    /**     * Категория     */
    val category: DicIncome? = null,

    /**     * Дата     */
    val date: LocalDate = LocalDate.now(),

    /**     * Сумма (в копейках)     */
    val sum: Long,

    /**     * Комментарий     */
    val comment: String? = null,
)