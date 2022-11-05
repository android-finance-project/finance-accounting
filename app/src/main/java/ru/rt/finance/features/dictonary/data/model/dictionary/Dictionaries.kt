package ru.rt.finance.features.dictonary.data.model.dictionary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *  PK
 * id
 * name
 * Категория доход
 * */
@Entity(tableName = Dictionary.tableNameDicIncome)
data class DicIncomeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_dic_income_entity")
    val idDicIncomeEntity: Int = 1,
    @ColumnInfo(name = "name_dic_income_entity")
    val nameDicIncomeEntity: String? = null,
    )

/**
 *PK
 *id
 8name
  Категория Расход */
@Entity(tableName = Dictionary.tableNameDicExpense)
data class DicExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_dic_expense_entity")
    val idDicExpenseEntity: Int=1,
    @ColumnInfo(name = "name_dic_expense_entity")
    val nameDicExpenseEntity: String? = null,
    )

object Dictionary {
    const val tableNameDicIncome = "dicincome"
    const val tableNameDicExpense = "dicexpense"
}