package ru.rt.finance.features.management.data.model.finance

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.features.user.data.model.UserEntity

/**
 *    Пользователь (дефолтный по умолчанию)
 *        user
 *      Категория
 *      category
 *      Дата
 *      LocalDate
 *      Сумма (в копейках)
 *      sum
 *      Комментарий
 *      String?
 * таблица для учета оперативных расходов */

@Entity(tableName = TableExpense.tableNameExpense)
data class ExpenseEntity(

    @PrimaryKey(autoGenerate  = true)
    val id: Int = 1,
    @Embedded
    val user: UserEntity = UserEntity(1,"ФИО"),
    @Embedded
    val category: DicExpenseEntity? = null,
    val date: String? = null,
    val sum: Long,
    val comment: String? = null,
)

/*
todo ???
  set relations of ORM
*/

object TableExpense {
    const val tableNameExpense = "expense"
}