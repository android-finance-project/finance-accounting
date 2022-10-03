package ru.rt.finance.features.dictonary.data.model.dictionary

import androidx.room.Entity
import androidx.room.PrimaryKey

/**  Категория доход */
@Entity(tableName = Dictionary.tableNameDicIncome)
data class DicIncomeEntity(
    /** PK */
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String? = null,

    )

/**  Категория Расход */
@Entity(tableName = Dictionary.tableNameDicExpense)
data class DicExpenseEntity(
    /** PK  */
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String? = null,

    )

object Dictionary {
    const val tableNameDicIncome = "dicincome"
    const val tableNameDicExpense = "dicexpense"
}