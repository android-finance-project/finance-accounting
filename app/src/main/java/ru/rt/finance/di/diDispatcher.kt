package ru.rt.finance.di
import  org.koin.core.qualifier.named
import  org.koin.dsl.module

import kotlinx.coroutines.Dispatchers

const val NmaOfDispatcherDefault = "Defalut"
const val NmaOfDispatcherIO = "IO"
const val NmaOfDispatcherMain = "Main"
class diDispatcher {
    val dispModules = module {
        single ( named(NmaOfDispatcherDefault) ) { Dispatchers.Default}
        single ( named(NmaOfDispatcherIO) ) { Dispatchers.IO}
        single ( named(NmaOfDispatcherMain) ) { Dispatchers.Main}
    }
}