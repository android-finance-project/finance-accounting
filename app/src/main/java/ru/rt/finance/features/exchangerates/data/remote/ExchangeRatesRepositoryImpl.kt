package ru.rt.finance.features.exchangerates.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import ru.rt.finance.features.exchangerates.data.model.ExchangeRateDto
import ru.rt.finance.features.exchangerates.domain.repository.ExchangeRatesRepository

private const val GET_RATES_PATH = "/latest"
private const val BASE_CURRENCY = "RUB"

/**
 * Репозиторий для запроса курса валют
 */
class ExchangeRatesRepositoryImpl(private val client: HttpClient) : ExchangeRatesRepository {

    override suspend fun getRates(): ExchangeRateDto =
        client.get(GET_RATES_PATH) { parameter("base", BASE_CURRENCY) }.body()
}