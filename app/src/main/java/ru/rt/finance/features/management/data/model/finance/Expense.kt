package ru.rt.finance.features.management.data.model.finance

import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense
import ru.rt.finance.features.user.data.model.User
import java.time.LocalDate

import androidx.room.Entity
import androidx.room.PrimaryKey

/** таблица для учета оперативных расходов */

@Entity(tableName = "Expense")
data class Expense(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    /**      Пользователь (дефолтный по умолчанию)     */
    val user: User,

    /**      Категория     */
    val category: DicExpense? = null,

    /**      Дата     */
    val date: LocalDate = LocalDate.now(),

    /**      Сумма (в копейках)     */
    val sum: Long,

    /**      Комментарий     */
    val comment: String? = null,
)

/*
todo ???
  set relations of ORM
*/