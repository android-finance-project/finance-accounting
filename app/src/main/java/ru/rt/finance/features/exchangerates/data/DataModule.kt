package ru.rt.finance.features.exchangerates.data

import org.koin.dsl.module
import ru.rt.finance.features.exchangerates.data.network.HttpClientBuilder
import ru.rt.finance.features.exchangerates.data.remote.ExchangeRatesRepositoryImpl
import ru.rt.finance.features.exchangerates.domain.repository.ExchangeRatesRepository

/* Для динамического запроса данных (каждую минуту). Требует API-Key */
@Suppress("unused")
private const val DYNAMICALLY_RATES_URL = "api.exchangeratesapi.io/v1"

/* Статические данные */
private const val EXCHANGE_RATES_URL = "api.exchangerate.host"

val exchangeData = module {
    single { HttpClientBuilder(EXCHANGE_RATES_URL).httpClient }
    single<ExchangeRatesRepository> { ExchangeRatesRepositoryImpl(get()) }
}