package ru.rt.finance.features.management.data.model.dictionary

/**
 * Категория доход
 */
data class DicIncome(
    /**
     * PK
     */
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
data class DicExpense(
    /**
     * PK
     */
    val id: Int,
    /**
     *
     */
    val name: String? = null,
    //val picture: URI
)