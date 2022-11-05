package ru.rt.finance.features.exchangerates.di

import org.koin.dsl.module
import ru.rt.finance.features.exchangerates.data.exchangeData
import ru.rt.finance.features.exchangerates.domain.exchangeDomain
import ru.rt.finance.features.exchangerates.presentation.exchangePresentation

val exchangeRatesModule = module {
    includes(exchangeData, exchangeDomain, exchangePresentation)
}

