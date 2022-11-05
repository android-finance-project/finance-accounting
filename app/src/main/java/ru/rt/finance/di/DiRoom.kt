package ru.rt.finance.di

import androidx.room.Room
import org.koin.dsl.module
import ru.rt.finance.core.localdatabase.FinanceInstantDatabase
import ru.rt.finance.core.localdatabase.ParametersDB.DATA_DB
import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import ru.rt.finance.core.MainApplication.Companion.TAG
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity
import ru.rt.finance.features.dictonary.data.model.dictionary.DicIncomeEntity
import ru.rt.finance.features.user.data.model.UserEntity

class DiRoom {

    companion object {

        private fun provideDataBase(context: Context): FinanceInstantDatabase {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                FinanceInstantDatabase::class.java,
                DATA_DB
            ).build()
            val applicationScope = CoroutineScope(SupervisorJob())
            applicationScope.launch {
                val userDao = instance.user()
                if (userDao.getRowCount() > 0) {
                    userDao.deleteAllUser()
                    Log.d(TAG, "userDao.deleteAllUser()")
                    userDao.addUser(
                        UserEntity(
                            name = "ФИО",
                        )
                    )
                    fillSetupData2DicExpenseEntity(instance)
                    fillSetupData2DicIncomeEntity(instance)
                }
            }
            return instance
        }

        //TODO: this function filling on setup data for the entity of dictonary that to describe the expense
        private suspend fun fillSetupData2DicExpenseEntity(instance: FinanceInstantDatabase) {
            val dicExpenseDao = instance.dicExpense()
            dicExpenseDao.deleteAllDicExpense()
            Log.d(TAG, "dicExpenseDao.deleteAllDicExpense()")
            val itemsDicExpenseEntity = listOf<DicExpenseEntity>(
                DicExpenseEntity(idDicExpenseEntity = 1, nameDicExpenseEntity = "Иные расходы"),
                DicExpenseEntity(idDicExpenseEntity = 2, nameDicExpenseEntity = "Услуги"),
                DicExpenseEntity(idDicExpenseEntity = 3, nameDicExpenseEntity = "Транспорт"),
                DicExpenseEntity(idDicExpenseEntity = 4, nameDicExpenseEntity = "Техника"),
                DicExpenseEntity(idDicExpenseEntity = 5, nameDicExpenseEntity = "Развлечения"),
                DicExpenseEntity(idDicExpenseEntity = 6, nameDicExpenseEntity = "Продукты питания"),
                DicExpenseEntity(idDicExpenseEntity = 7, nameDicExpenseEntity = "Одежда"),
                DicExpenseEntity(idDicExpenseEntity = 8, nameDicExpenseEntity = "Обувь"),
                DicExpenseEntity(idDicExpenseEntity = 9, nameDicExpenseEntity = "Медицина"),
                DicExpenseEntity(idDicExpenseEntity = 10, nameDicExpenseEntity = "Мебель"),
                DicExpenseEntity(idDicExpenseEntity = 11, nameDicExpenseEntity = "Коммунальные услуги"),
                DicExpenseEntity(idDicExpenseEntity = 12, nameDicExpenseEntity = "Комиссия"),
                DicExpenseEntity(idDicExpenseEntity = 13, nameDicExpenseEntity = "Автомобиль")
            )
            dicExpenseDao.addDicExpenseAll(*itemsDicExpenseEntity.toTypedArray())
        }

        //TODO: this function filling on setup data for the entity of dictonary that to describe the income
        private suspend fun fillSetupData2DicIncomeEntity(instance: FinanceInstantDatabase) {
            val dicIncomeDao = instance.dicIncome()
            dicIncomeDao.deleteAllDicIncome()
            Log.d(TAG, "dicIncomeDao.deleteAllDicIncome()")
            val itemsDicIncomeEntity = listOf<DicIncomeEntity>(
                DicIncomeEntity(idDicIncomeEntity = 1, nameDicIncomeEntity = "Иные доходы"),
                DicIncomeEntity(idDicIncomeEntity = 2, nameDicIncomeEntity = "Аренда жилья"),
                DicIncomeEntity(idDicIncomeEntity = 3, nameDicIncomeEntity = "Дивиденды"),
                DicIncomeEntity(idDicIncomeEntity = 4, nameDicIncomeEntity = "Дотация"),
                DicIncomeEntity(idDicIncomeEntity = 5, nameDicIncomeEntity = "Зарплата"),
                DicIncomeEntity(idDicIncomeEntity = 6, nameDicIncomeEntity = "Лотерея"),
                DicIncomeEntity(idDicIncomeEntity = 7, nameDicIncomeEntity = "Материальная помощь"),
                DicIncomeEntity(idDicIncomeEntity = 8, nameDicIncomeEntity = "Наследство"),
                DicIncomeEntity(idDicIncomeEntity = 9, nameDicIncomeEntity = "Находка"),
                DicIncomeEntity(idDicIncomeEntity = 10, nameDicIncomeEntity = "Пенсия"),
                DicIncomeEntity(idDicIncomeEntity = 11, nameDicIncomeEntity = "Подарок"),
                DicIncomeEntity(idDicIncomeEntity = 12, nameDicIncomeEntity = "Продажа имущества"),
                DicIncomeEntity(idDicIncomeEntity = 13, nameDicIncomeEntity = "Стипендия"),
                DicIncomeEntity(idDicIncomeEntity = 14, nameDicIncomeEntity = "Страховка"),
                DicIncomeEntity(idDicIncomeEntity = 15, nameDicIncomeEntity = "Халтура")
            )
            dicIncomeDao.addDicIncomeAll(*itemsDicIncomeEntity.toTypedArray())
        }

        val roomModule = module {
            single { provideDataBase(androidContext()) }
            single { get<FinanceInstantDatabase>().dicExpense() }
            single { get<FinanceInstantDatabase>().dicIncome() }
            single { get<FinanceInstantDatabase>().expense() }
            single { get<FinanceInstantDatabase>().income() }
            single { get<FinanceInstantDatabase>().user() }

        }
    }
}
