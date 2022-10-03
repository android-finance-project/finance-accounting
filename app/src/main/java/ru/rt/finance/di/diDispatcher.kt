package ru.rt.finance.di

import  org.koin.core.qualifier.named
import  org.koin.dsl.module
import kotlinx.coroutines.Dispatchers

class DiDispatcher {
    val dispatcherModules = module {
        single(named(DispatcherConstants.DISPATCHER_DEFAULT)) { Dispatchers.Default }
        single(named(DispatcherConstants.DISPATCHER_IO)) { Dispatchers.IO }
        single(named(DispatcherConstants.DISPATCHER_MAIN)) { Dispatchers.Main }
    }
}