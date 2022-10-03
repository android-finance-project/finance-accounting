package ru.rt.finance.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.rt.finance.features.dictonary.DicExpenseRepository
import ru.rt.finance.features.dictonary.DicIncomeRepository
import ru.rt.finance.features.management.data.ManagementRepository
import ru.rt.finance.features.user.data.userRepository

class diRepository {

    val repositoryModule =
        module{
            single { userRepository(get(), get(named("NmaOfDispatcherIO" ))) }
            single {DicExpenseRepository(get(), get(named("NmaOfDispatcherIO" )))}
            single { DicIncomeRepository(get(), get(named("NmaOfDispatcherIO" ))) }
            single {
                ManagementRepository.ExpenseRepository(
                    get(),
                    get(named("NmaOfDispatcherIO"))
                )
            }
            single {
                ManagementRepository.IncomeRepository(
                    get(),
                    get(named("NmaOfDispatcherIO"))
                )
            }







            }

        }


