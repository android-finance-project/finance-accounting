package ru.rt.finance.features.exchangerates.domain

import org.koin.dsl.module
import ru.rt.finance.features.exchangerates.domain.usecases.LoadExchangeRatesUseCase

val exchangeDomain = module {
    single{ LoadExchangeRatesUseCase(get()) }
}