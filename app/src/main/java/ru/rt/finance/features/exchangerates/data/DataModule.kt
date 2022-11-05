package ru.rt.finance.features.exchangerates.data

import org.koin.dsl.module
import ru.rt.finance.features.exchangerates.data.network.HttpClientBuilder
import ru.rt.finance.features.exchangerates.data.remote.ExchangeRatesRepositoryImpl
import ru.rt.finance.features.exchangerates.domain.repository.ExchangeRatesRepository

val exchangeData = module {
    single { HttpClientBuilder("https://api.exchangeratesapi.io/v1").httpClient }
    single<ExchangeRatesRepository> { ExchangeRatesRepositoryImpl(get()) }
}