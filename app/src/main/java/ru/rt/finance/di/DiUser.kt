package ru.rt.finance.di

import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.rt.finance.features.dictonary.DicExpenseRepository
import ru.rt.finance.features.dictonary.domain.LoadDicExpensesUseCase
import ru.rt.finance.features.dictonary.presentation.ui.AddDicExpenseFragment
import ru.rt.finance.features.dictonary.presentation.ui.DicExpenseFragment
import ru.rt.finance.features.dictonary.presentation.ui.EditDicExpenseFragment
import ru.rt.finance.features.dictonary.presentation.viewmodel.AddDicExpenseViewModel
import ru.rt.finance.features.dictonary.presentation.viewmodel.DicExpenseViewModel
import ru.rt.finance.features.dictonary.presentation.viewmodel.EditDicExpenseViewModel
import ru.rt.finance.features.user.data.UserRepository
import ru.rt.finance.features.user.data.presentation.ui.activity.viewmodel.UserViewModel
import ru.rt.finance.features.user.domain.LoadUsersUseCase
import ru.rt.finance.features.user.presentation.ui.UsersFragment

class DiUser {
    companion object {
        val usersModule = module {
            fragment { UsersFragment() }

            viewModel {
                UserViewModel(
                    loadUsersUseCase = get(),
                    ioDispatcher = get(named(DispatcherConstants.DISPATCHER_IO))
                )
            }

            factory { LoadUsersUseCase(usersRepository = get()) }

            single<UserRepository> {
                UserRepository(
                    userSource = get(),
                    dispatcher = get(named(DispatcherConstants.DISPATCHER_IO))
                )
            }

        }

        val dicExpenseModule = module {
            fragment { DicExpenseFragment() }
            fragment { AddDicExpenseFragment() }
            fragment { EditDicExpenseFragment() }
            viewModel {
                DicExpenseViewModel(
                    loadDicExpensesUseCase = get(),
                    ioDispatcher = get(named(DispatcherConstants.DISPATCHER_IO))
                )
            }
            viewModel {
                AddDicExpenseViewModel(
                    dicExpenseRepository = get()
                )
            }
            viewModel {
                EditDicExpenseViewModel(
                    dicExpenseRepository = get()
                )
            }

            factory { LoadDicExpensesUseCase(dicExpenseRepository = get()) }

            single<DicExpenseRepository> {
                DicExpenseRepository(
                    dicExpenseSource = get(),
                    dispatcher = get(named(DispatcherConstants.DISPATCHER_IO))
                )
            }

        }
    }
}