package ru.rt.finance.di

import org.koin.dsl.module
import ru.rt.finance.features.exchangerates.di.exchangeRatesModule

val appModule = module {
    includes(exchangeRatesModule)
}

