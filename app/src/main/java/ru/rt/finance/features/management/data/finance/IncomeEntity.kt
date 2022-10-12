package ru.rt.finance.features.management.data.model.finance

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncomeEntity
import ru.rt.finance.features.user.data.model.UserEntity

/**  Таблица для оперативного учета доходов */

@Entity(tableName = TableIncome.tableNameIncome)
data class IncomeEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    /**      Пользователь (дефолтный по умолчанию)     */
    val user: UserEntity,

    /**      Категория     */
    val category: DicIncomeEntity? = null,

    /**     Дата     */
    val date: LocalDate = LocalDate.now(),

    /**     Сумма (в копейках)     */
    val sum: Long,

    /**      Комментарий     */
    val comment: String? = null,
)

object TableIncome {
    const val tableNameIncome = "income"
}