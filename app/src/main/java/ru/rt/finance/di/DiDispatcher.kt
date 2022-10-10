package ru.rt.finance.di
import  org.koin.core.qualifier.named
import  org.koin.dsl.module

import kotlinx.coroutines.Dispatchers

const val dispatchersDefault = "Default"
const val dispatchersIO = "IO"
const val dispatchersMain = "Main"
class DiDispatcher {
    val dispModules = module {
        single ( named(dispatchersDefault) ) { Dispatchers.Default}
        single ( named(dispatchersIO) ) { Dispatchers.IO}
        single ( named(dispatchersMain) ) { Dispatchers.Main}
    }
}