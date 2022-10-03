package ru.rt.finance.features.management.data.model.finance

import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpense
import ru.rt.finance.features.user.data.model.User
import java.time.LocalDate

//import androidx.room.*
//import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
//import kotlinx.android.parcel.Parcelize

/**
 * Расход
 */




//@Parcelize
@Entity(tableName = "Expense")
data class Expense(

    /**
     * PK
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    /**
     * Пользователь (дефолтный по умолчанию)
     */
    val user: User,

    /**
     * Категория
     */
    val category: DicExpense? = null,

    /**
     * Дата
     */
    val date: LocalDate = LocalDate.now(),

    /**
     * Сумма (в копейках)
     */
    val sum: Long,

    /**
     * Комментарий
     */
    val comment: String? = null,
)

/*
todo ???
@Entity(foreignKeys = @ForeignKey(
    entity = Expense.class,
            parentColumns = "id",
             childColumns = "category")
)
*/