package ru.rt.finance.di

import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
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
                    loadOnlineUsersUseCase = get(),
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
    }
}