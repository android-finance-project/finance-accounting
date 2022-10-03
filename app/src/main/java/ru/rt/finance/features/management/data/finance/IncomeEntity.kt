package ru.rt.finance.features.management.data.model.finance

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncomeEntity
import ru.rt.finance.features.user.data.model.UserEntity

/**
 *      Пользователь (дефолтный по умолчанию)
 *      UserEntity,
 *      Категория
 *      DicIncomeEntity
 *      Дата
 *      LocalDate
 *      Сумма (в копейках)
 *      Long
 *      Комментарий
 *      comment
 * Таблица для оперативного учета доходов */

@Entity(tableName = TableIncome.tableNameIncome)
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded
    val user: UserEntity=UserEntity(1,"ФИО"),
    @Embedded
    val category: DicIncomeEntity? = null,
    val date: String? = null,
    val sum: Long,
    val comment: String? = null,
)

object TableIncome {
    const val tableNameIncome = "income"
}