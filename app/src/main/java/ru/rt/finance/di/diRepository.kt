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
            single { UserRepository(get(), get(named(DispatcherConstants.DISPATCHER_IO))) }
            single { DicExpenseRepository(get(), get(named(DispatcherConstants.DISPATCHER_IO))) }
            single { DicIncomeRepository(get(), get(named(DispatcherConstants.DISPATCHER_IO))) }
            single { ExpenseRepository(get(),get(named(DispatcherConstants.DISPATCHER_IO))) }
            single { IncomeRepository(get(),get(named(DispatcherConstants.DISPATCHER_IO)))  }
        }
}

object DispatcherConstants {
    const val DISPATCHER_IO = "dispatcherIO"
    // how kick name from context
}