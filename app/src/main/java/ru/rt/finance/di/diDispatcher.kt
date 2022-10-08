package ru.rt.finance.di

import  org.koin.core.qualifier.named
import  org.koin.dsl.module
import kotlinx.coroutines.Dispatchers

const val dispatcherDefault = "Default"
const val dispatcherIO = "IO"
const val dispatcherMain = "Main"

class DiDispatcher {
    val dispatcherModules = module {
        single(named(dispatcherDefault)) { Dispatchers.Default }
        single(named(dispatcherIO)) { Dispatchers.IO }
        single(named(dispatcherMain)) { Dispatchers.Main }
    }
}