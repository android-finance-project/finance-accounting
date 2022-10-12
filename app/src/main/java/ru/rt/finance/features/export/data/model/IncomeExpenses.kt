package ru.rt.finance.features.export.data.model

//todo
data class ExportIncomeExpense(
    // Структура для файлов Экспорта
    val id: Int,
    val idPerson: Int,
    val category: Int? = null,
    val flagCategory: Int? = null,
    val date: String? = null,
    val sum: Double? = null,
    val comment: String? = null,
)

//todo
data class ImportIncomeExpense(
    // Структура для файлов Импорта
    val id: Int,
    val category: Int? = null,
    val flagCategory: Int? = null,
    val date: String? = null,
    val sum: Double? = null,
    val comment: String? = null,
)