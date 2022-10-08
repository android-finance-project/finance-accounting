package ru.rt.finance.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.rt.finance.features.dictonary.DicExpenseRepository
import ru.rt.finance.features.dictonary.DicIncomeRepository
import ru.rt.finance.features.management.data.ExpenseRepository
import ru.rt.finance.features.management.data.IncomeRepository
import ru.rt.finance.features.user.data.UserRepository

class DiRepository {

    val repositoryModule =
        module {
            single { UserRepository(get(), get(named("dispatchersIO"))) }
            single { DicExpenseRepository(get(), get(named("dispatchersIO"))) }
            single { DicIncomeRepository(get(), get(named("dispatchersIO"))) }
            single {
                ExpenseRepository(
                    get(),
                    get(named("dispatchersIO"))
                )
            }
            single {
                IncomeRepository(
                    get(),
                    get(named("dispatchersIO"))
                )
            }
        }
}


