package ru.rt.finance.features.management.data.model.finance

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.features.user.data.model.UserEntity

/** таблица для учета оперативных расходов */

@Entity(tableName = TableExpense.tableNameExpense)
data class ExpenseEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    /**      Пользователь (дефолтный по умолчанию)     */
    val user: UserEntity,

    /**      Категория     */
    val category: DicExpenseEntity? = null,

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

object TableExpense {
    const val tableNameExpense = "expense"
}