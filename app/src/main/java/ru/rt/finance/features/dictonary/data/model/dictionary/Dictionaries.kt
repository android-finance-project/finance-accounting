package ru.rt.finance.features.dictonary.data.model.dictionary
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Категория доход
 */
@Entity(tableName = Dictonary.tableNameDicIncome)
data class DicIncome(
    /**
     * PK
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    /**
     *
     */
    val name: String? = null,

    //val picture: URI
)

/**
 * Категория Расход
 */
@Entity(tableName = Dictonary.tableNameDicExpense)
data class DicExpense(
    /**
     * PK
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    /**
     *
     */
    val name: String? = null,
    //val picture: URI
)

object  Dictonary {
    const val tableNameDicIncome = "dicincome"
    const val tableNameDicExpense = "dicexpense"
}