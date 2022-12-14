package ru.rt.finance.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.rt.finance.di.DiDispatcher.Companion.dispatcherModules
import ru.rt.finance.di.DiRepository.Companion.repositoryModule
import ru.rt.finance.di.DiRoom.Companion.roomModule
import ru.rt.finance.di.DiUser.Companion.dicExpenseModule
import ru.rt.finance.di.DiUser.Companion.usersModule

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            androidFileProperties()
            fragmentFactory()
            modules(dispatcherModules, roomModule, repositoryModule, usersModule, dicExpenseModule)
        }
    }

    companion object {
        val TAG = "MyTag"
    }
}